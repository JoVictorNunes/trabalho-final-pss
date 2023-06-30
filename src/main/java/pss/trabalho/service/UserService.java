package pss.trabalho.service;

import pss.trabalho.CurrentUser;
import pss.trabalho.exceptions.DuplicatedException;
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
    public void readNotification(Notification notification) {
        notification.setRead(true);
        notificationRepository.update(notification);
    }

    @Override
    public void sendNotification(String message, UUID to) {

    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String newPasswordConformation) {

    }
}
