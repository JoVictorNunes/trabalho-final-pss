package pss.trabalho.service;

import com.pss.senha.validacao.ValidadorSenha;
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

    public User signUp(String name, String password, String passwordConfirmation) throws InvalidPasswordException, DuplicatedException, InvalidNameException {
        if (name.length() < 3) {
            throw new InvalidNameException("O nome precisa ter no mínimo 3 caracteres");
        }
        List<User> users = userRepository.getAll();
        User existingUser = extractUserByName(users, name);
        if (existingUser != null) {
            throw new DuplicatedException("Nome de usuário não disponível.");
        }
        if (!password.equals(passwordConfirmation)) throw new InvalidPasswordException("Senha e confirmação de senha são diferentes.");
        ValidadorSenha validadorSenha = new ValidadorSenha();
        List<String> validationResult = validadorSenha.validar(password);
        if (!validationResult.isEmpty()) {
            throw new InvalidPasswordException(validationResult.get(0));
        }
        boolean isFirstUser = users.size() == 0;
        User user = new User(
                UUID.randomUUID(),
                name,
                password,
                new Date().getTime(),
                isFirstUser,
                isFirstUser,
                0
        );
        userRepository.create(user);
        CurrentUser.setInstance(user);
        return user;
    }

    @Override
    public User signIn(String name, String password) throws InvalidPasswordException, NotFoundException, UnauthorizedException {
        List<User> users = userRepository.getAll();
        User existingUser = extractUserByName(users, name);
        if (existingUser == null) {
            throw new NotFoundException("Esse usuário não existe.");
        }
        if (!password.equals(existingUser.getPassword())) {
            throw new InvalidPasswordException("Senha errada.");
        }
        if (!existingUser.isAuthorized()) {
            throw new UnauthorizedException("Usuário não autorizado pelo administrador");
        }
        CurrentUser.setInstance(existingUser);
        return existingUser;
    }

    public void signOut() {
        CurrentUser.setInstance(null);
    }

    private User extractUserByName(List<User> users, String name) {
        for (User user : users) {
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
            User currentUser = CurrentUser.getInstance();
            if (!oldPassword.equals(currentUser.getPassword())) throw new InvalidPasswordException("Senha errada.");
            if (!newPassword.equals(newPasswordConfirmation)) throw new InvalidPasswordException("Senha e confirmação de senha são diferentes.");
            ValidadorSenha validadorSenha = new ValidadorSenha();
            List<String> validationResult = validadorSenha.validar(newPassword);
            if (!validationResult.isEmpty()) {
                throw new InvalidPasswordException(validationResult.get(0));
            }
            userRepository.update(currentUser);
            currentUser.setPassword(newPassword);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível atualizar a senha. Tente novamente.");
        }
    }

    @Override
    public User signUserUp(String name, String password, String passwordConfirmation) throws InvalidNameException, InvalidPasswordException, DuplicatedException {
        if (name.length() < 3) {
            throw new InvalidNameException("O nome precisa ter no mínimo 3 caracteres");
        }
        List<User> users = userRepository.getAll();
        User existingUser = extractUserByName(users, name);
        if (existingUser != null) {
            throw new DuplicatedException("Nome de usuário não disponível.");
        }
        if (!password.equals(passwordConfirmation)) throw new InvalidPasswordException("Senha e confirmação de senha são diferentes.");
        ValidadorSenha validadorSenha = new ValidadorSenha();
        List<String> validationResult = validadorSenha.validar(password);
        if (!validationResult.isEmpty()) {
            throw new InvalidPasswordException(validationResult.get(0));
        }
        User user = new User(
                UUID.randomUUID(),
                name,
                password,
                new Date().getTime(),
                false,
                true,
                0
        );
        userRepository.create(user);
        return user;
    }

    public void changeLogType(int logType) {
        CurrentUser.getInstance().setLogType(logType);
        userRepository.update(CurrentUser.getInstance());
    }

    public void authorizeUser(UUID userId) {
        User user = userRepository.getById(userId);
        user.setAuthorized(true);
        userRepository.update(user);
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public INotificationRepository getNotificationRepository() {
        return notificationRepository;
    }
}
