package pss.trabalho.service;

import pss.trabalho.exceptions.*;
import pss.trabalho.model.User;
import pss.trabalho.repository.INotificationRepository;
import pss.trabalho.repository.IUserRepository;

import java.util.UUID;

public interface IUserService {
     User signUp(String name, String password, String passwordConfirmation) throws InvalidPasswordException, DuplicatedException, InvalidNameException;
     User signIn(String name, String password) throws InvalidPasswordException, NotFoundException, UnauthorizedException;
     void signOut();
     void readNotification(UUID notificationId);
     void sendNotification(String message, UUID to) throws UnauthorizedException;
     void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException, InvalidPasswordException;
     User signUserUp(String name, String password, String passwordConfirmation) throws InvalidNameException, InvalidPasswordException, DuplicatedException;
     void changeLogType(int logType);
     void authorizeUser(UUID userId);
     IUserRepository getUserRepository();
     INotificationRepository getNotificationRepository();
}
