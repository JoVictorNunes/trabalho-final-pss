package pss.trabalho.service;

import pss.trabalho.exceptions.*;
import pss.trabalho.model.User;

import java.util.UUID;

public interface IUserService {
     User signUp(String name, String password, String passwordConfirmation) throws InvalidPasswordException, DuplicatedException, InvalidNameException;
     User signIn(String name, String password) throws InvalidPasswordException, NotFoundException;
     void readNotification(UUID notificationId);
     void sendNotification(String message, UUID to) throws UnauthorizedException;
     void updatePassword(String oldPassword, String newPassword, String newPasswordConfirmation) throws RuntimeException, InvalidPasswordException;
     void signUserUp(String name, String password) throws UnauthorizedException;
}
