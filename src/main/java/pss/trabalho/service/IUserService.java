package pss.trabalho.service;

import pss.trabalho.exceptions.*;
import pss.trabalho.model.User;

import java.util.UUID;

public interface IUserService {
     User signIn(String name, String password) throws DuplicatedException;
     void readNotification(UUID notificationId);
     void sendNotification(String message, UUID to) throws UnauthorizedException;
     void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException, InvalidPasswordException;
     void signUserUp(String name, String password) throws UnauthorizedException;
}
