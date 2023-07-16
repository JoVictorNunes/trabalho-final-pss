package pss.trabalho.repository;

import pss.trabalho.dao.INotificationDAO;
import pss.trabalho.dao.IUserDAO;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IUserRepository {
    private final IUserDAO userDAO;
    private final INotificationDAO notificationDAO;
    private final List<UserRepositoryObserver> observers;

    public UserRepository(IUserDAO userDAO, INotificationDAO notificationDAO) {
        this.userDAO = userDAO;
        this.notificationDAO = notificationDAO;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(UserRepositoryObserver observer) {
        this.observers.add(observer);
    }

    private void notify(RepositoryEvents event, User user) {
        for (UserRepositoryObserver observer : this.observers) {
            observer.onUserRepositoryChange(event, user);
        }
    }

    @Override
    public void create(User user) throws RuntimeException {
        try {
            userDAO.create(user);
            this.notify(RepositoryEvents.CREATE, user);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't create user.");
        }
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> users = userDAO.readAll();
            users.forEach(user -> {
                List<Notification> userNotifications = notificationDAO.readByUserId(user.getId());
                user.setNotificationList(userNotifications);
            });
            return users;
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't get users.");
        }
    }

    @Override
    public User getById(UUID id) {
        try {
            User user = userDAO.readById(id);
            List<Notification> userNotifications = notificationDAO.readByUserId(user.getId());
            user.setNotificationList(userNotifications);
            return user;
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't get user.");
        }
    }

    @Override
    public void update(User user) {
        try {
            userDAO.update(user);
            this.notify(RepositoryEvents.UPDATE, user);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't update user.");
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            User user = this.getById(id);
            System.out.println(user);
            userDAO.delete(id);
            this.notify(RepositoryEvents.DELETE, user);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Couldn't delete user.");
        }
    }
}
