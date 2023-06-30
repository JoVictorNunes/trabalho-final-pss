package pss.trabalho.service;

import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;

import java.util.UUID;

public interface IUserService {
     User signIn(String name, String password) throws DuplicatedException;
     void readNotification(Notification notification);
     void sendNotification(String message, UUID to);
     void updatePassword(String oldPassword, String newPassword, String newPasswordConformation);
}
