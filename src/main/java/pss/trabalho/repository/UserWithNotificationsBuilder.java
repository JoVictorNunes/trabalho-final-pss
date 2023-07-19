package pss.trabalho.repository;

import pss.trabalho.factory.INotificationDAO;
import pss.trabalho.factory.IUserDAO;
import pss.trabalho.model.Notification;

import java.util.List;
import java.util.UUID;

public class UserWithNotificationsBuilder extends UserBuilder {
    public UserWithNotificationsBuilder(UUID userId, IUserDAO userDAO, INotificationDAO notificationDAO) {
        super(userId, userDAO, notificationDAO);
    }

    @Override
    public void addUser() {
        user = userDAO.readById(userId);
    }

    @Override
    public void addNotifications() {
        List<Notification> userNotifications = notificationDAO.readByUserId(user.getId());
        user.setNotificationList(userNotifications);
    }
}
