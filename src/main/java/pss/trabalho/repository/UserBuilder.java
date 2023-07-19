package pss.trabalho.repository;

import pss.trabalho.factory.INotificationDAO;
import pss.trabalho.factory.IUserDAO;
import pss.trabalho.model.User;

import java.util.UUID;

public abstract class UserBuilder {
    protected User user;
    protected IUserDAO userDAO;
    protected INotificationDAO notificationDAO;
    protected UUID userId;

    public UserBuilder(UUID userId, IUserDAO userDAO, INotificationDAO notificationDAO) {
        this.userDAO = userDAO;
        this.notificationDAO = notificationDAO;
        this.userId = userId;
    }

    public abstract void addUser();
    public abstract void addNotifications();
    public final User getUser() {
        return user;
    }
}
