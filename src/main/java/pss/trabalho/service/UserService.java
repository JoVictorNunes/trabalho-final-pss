package pss.trabalho.service;

import pss.trabalho.CurrentUser;
import pss.trabalho.exceptions.*;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;
import pss.trabalho.repository.INotificationRepository;
import pss.trabalho.repository.IUserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final INotificationRepository notificationRepository;

    public UserService(IUserRepository userRepository, INotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public User signIn(String name, String password) throws DuplicatedException {
        List<User> users = userRepository.getAll();
        User existingUser = extractUserByName(users, name);
        if (existingUser != null && !password.equals(existingUser.getPassword())) {
            throw new DuplicatedException("Duplicated user.");
        }
        if (existingUser != null && password.equals(existingUser.getPassword())) {
            CurrentUser.setInstance(existingUser);
            return existingUser;
        }
        boolean isFirstUser = users.size() == 0;
        User user = new User(
                UUID.randomUUID(),
                name,
                password,
                new Date().getTime(),
                isFirstUser,
                isFirstUser
        );
        userRepository.create(user);
        CurrentUser.setInstance(user);
        return user;
    }

    private User extractUserByName(List<User> users, String name) {
        for (User user: users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void readNotification(UUID notificationId) {
        Notification notification = notificationRepository.getById(notificationId);
        notification.setRead(true);
        notificationRepository.update(notification);
    }

    @Override
    public void sendNotification(String message, UUID to) throws UnauthorizedException {
        if (CurrentUser.getInstance().isAdmin()) {
            Notification notification = new Notification(UUID.randomUUID(), message, to, false);
            notificationRepository.create(notification);
            return;
        }
        throw new UnauthorizedException("You are not allowed to send notifications.");
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException, InvalidPasswordException {
        try {
            User currentUser = userRepository.getById(CurrentUser.getInstance().getId());
            if (!currentUser.getPassword().equals(oldPassword) || !newPassword.equals(newPasswordConfirmation)) {
                throw new InvalidPasswordException("Invalid password or the new password and new password confirmation don't match each other.");
            }
            currentUser.setPassword(newPassword);
            userRepository.update(currentUser);
            CurrentUser.setInstance(currentUser);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't update password.");
        }
    }

    @Override
    public void signUserUp(String name, String password) throws UnauthorizedException {
        if (CurrentUser.getInstance().isAdmin()) {
            User user = new User(
                    UUID.randomUUID(),
                    name,
                    password,
                    new Date().getTime(),
                    false,
                    true
            );
        }
        throw new UnauthorizedException("You are not allowed to sign up users.");
    }
}
