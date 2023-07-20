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
     void readNotification(UUID notificationId) throws RuntimeException;
     void sendNotification(String message, UUID to) throws RuntimeException;
     void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException;
     User signUserUp(String name, String password, String passwordConfirmation) throws RuntimeException;
     void changeLogType(int logType) throws RuntimeException;
     void authorizeUser(UUID userId) throws RuntimeException;
     void delete(UUID userId) throws RuntimeException;
     void update(UUID userId, String name, String password, String newPassword, String newPasswordConfirmation) throws RuntimeException;
     IUserRepository getUserRepository();
     INotificationRepository getNotificationRepository();
}
