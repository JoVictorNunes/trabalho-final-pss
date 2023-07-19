package pss.trabalho.service;

import com.pss.senha.validacao.ValidadorSenha;
import com.ufes.logger.log.LogFactory;
import com.ufes.logger.model.LogRecord;
import com.ufes.logger.service.LogService;
import pss.trabalho.CurrentUser;
import pss.trabalho.config.AppConfig;
import pss.trabalho.exceptions.*;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;
import pss.trabalho.repository.INotificationRepository;
import pss.trabalho.repository.IUserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final INotificationRepository notificationRepository;
    private final LogService logService;

    public UserService(IUserRepository userRepository, INotificationRepository notificationRepository, LogService logService) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.logService = logService;
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
        this.logService.setLogger(LogFactory.create(existingUser.getLogType(), AppConfig.getInstance().get("LOG_OUTPUT")));
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
    public void readNotification(UUID notificationId) throws RuntimeException {
        try {
            Notification notification = notificationRepository.getById(notificationId);
            notification.setRead(true);
            notificationRepository.update(notification);
            LogRecord record = new LogRecord("notification:sent", CurrentUser.getInstance().getName(), CurrentUser.getInstance().getName());
            this.logService.log(record);
        } catch (IOException e) {
            System.err.println("Log system failed.");
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível marcar a notificação como lida.");
        }
    }

    @Override
    public void sendNotification(String message, UUID to) throws RuntimeException {
        try {
            if (!CurrentUser.getInstance().isAdmin()) throw new UnauthorizedException("Você não tem permissão para enviar mensagens.");
            Notification notification = new Notification(UUID.randomUUID(), message, to, false);
            User user = userRepository.getById(to);
            notificationRepository.create(notification);
            LogRecord record = new LogRecord("notification:sent", user.getName(), CurrentUser.getInstance().getName());
            this.logService.log(record);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível enviar a notificação.");
        } catch (UnauthorizedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.err.println("Log system failed.");
        }
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException {
        try {
            User currentUser = userRepository.getById(CurrentUser.getInstance().getId());
            if (!oldPassword.equals(currentUser.getPassword())) throw new InvalidPasswordException("Senha errada.");
            if (!newPassword.equals(newPasswordConfirmation)) throw new InvalidPasswordException("Senha e confirmação de senha são diferentes.");
            ValidadorSenha validadorSenha = new ValidadorSenha();
            List<String> validationResult = validadorSenha.validar(newPassword);
            if (!validationResult.isEmpty()) {
                throw new InvalidPasswordException(validationResult.get(0));
            }
            currentUser.setPassword(newPassword);
            userRepository.update(currentUser);
            CurrentUser.getInstance().setPassword(newPassword);
            LogRecord record = new LogRecord("user:updatePassword", CurrentUser.getInstance().getName(), CurrentUser.getInstance().getName());
            this.logService.log(record);
        } catch (InvalidPasswordException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível atualizar a senha. Tente novamente.");
        } catch (IOException e) {
            System.err.println("Log system failed.");
        }
    }

    @Override
    public User signUserUp(String name, String password, String passwordConfirmation) throws RuntimeException {
        try {
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
            try {
                LogRecord record = new LogRecord("user:create", user.getName(), CurrentUser.getInstance().getName());
                this.logService.log(record);
            } catch (IOException e) {
                System.err.println("Log system failed.");
            }
            return user;
        } catch (InvalidNameException | InvalidPasswordException | DuplicatedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível cadastrar o usuário.");
        }
    }

    public void changeLogType(int logType) throws RuntimeException {
        try {
            User user = userRepository.getById(CurrentUser.getInstance().getId());
            user.setLogType(logType);
            userRepository.update(user);
            this.logService.setLogger(LogFactory.create(logType, AppConfig.getInstance().get("LOG_OUTPUT")));
            CurrentUser.getInstance().setLogType(logType);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível atualizar a preferência de log.");
        }
    }

    public void authorizeUser(UUID userId) throws RuntimeException {
        try {
            User user = userRepository.getById(userId);
            user.setAuthorized(true);
            userRepository.update(user);
            LogRecord record = new LogRecord("user:create", user.getName(), CurrentUser.getInstance().getName());
            this.logService.log(record);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível autorizar o usuário.");
        } catch (IOException e) {
            System.err.println("Log system failed");
        }
    }

    @Override
    public void delete(UUID userId) throws RuntimeException {
        try {
            User user = userRepository.getById(userId);
            userRepository.delete(userId);
            LogRecord record = new LogRecord("user:delete", user.getName(), CurrentUser.getInstance().getName());
            this.logService.log(record);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível excluir o usuário.");
        } catch (IOException e) {
            System.err.println("Log system failed");
        }
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public INotificationRepository getNotificationRepository() {
        return notificationRepository;
    }
}
