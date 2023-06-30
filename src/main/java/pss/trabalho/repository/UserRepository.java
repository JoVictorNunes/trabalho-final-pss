package pss.trabalho.repository;

import pss.trabalho.dao.INotificationDAO;
import pss.trabalho.dao.IUserDAO;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;

import java.util.List;

public class UserRepository implements IUserRepository {
    private final IUserDAO userDAO;
    private final INotificationDAO notificationDAO;

    public UserRepository(IUserDAO userDAO, INotificationDAO notificationDAO) {
        this.userDAO = userDAO;
        this.notificationDAO = notificationDAO;
    }

    @Override
    public void create(User user) {
        userDAO.create(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userDAO.readAll();
        users.forEach(user -> {
            List<Notification> userNotifications = notificationDAO.readByUserId(user.getId());
            user.setNotificationList(userNotifications);
        });
        return users;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}
