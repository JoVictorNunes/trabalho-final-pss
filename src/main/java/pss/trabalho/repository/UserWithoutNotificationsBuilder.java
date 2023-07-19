package pss.trabalho.repository;

import pss.trabalho.dao.INotificationDAO;
import pss.trabalho.dao.IUserDAO;

import java.util.UUID;

public class UserWithoutNotificationsBuilder extends UserBuilder {
    public UserWithoutNotificationsBuilder(UUID userId, IUserDAO userDAO, INotificationDAO notificationDAO) {
        super(userId, userDAO, notificationDAO);
    }

    @Override
    public void addUser() {
        user = userDAO.readById(userId);
    }

    @Override
    public void addNotifications() {

    }
}
