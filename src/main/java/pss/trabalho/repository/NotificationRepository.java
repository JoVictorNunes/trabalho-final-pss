package pss.trabalho.repository;

import pss.trabalho.factory.INotificationDAO;
import pss.trabalho.model.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationRepository implements INotificationRepository {
    private final INotificationDAO notificationDAO;
    private final List<NotificationRepositoryObserver> observers;

    public NotificationRepository(INotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(NotificationRepositoryObserver observer) {
        this.observers.add(observer);
    }

    private void notify(RepositoryEvents event, Notification notification) {
        for (NotificationRepositoryObserver observer : observers) {
            observer.onNotificationRepositoryChange(event, notification);
        }
    }

    @Override
    public void create(Notification notification) throws RuntimeException {
        try {
            notificationDAO.create(notification);
            this.notify(RepositoryEvents.CREATE, notification);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't create notification.");
        }
    }

    @Override
    public List<Notification> getAll() throws RuntimeException {
        try {
            return notificationDAO.readAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't get notifications.");
        }
    }

    @Override
    public Notification getById(UUID id) throws RuntimeException {
        try {
            return notificationDAO.readById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't get notification.");
        }
    }

    @Override
    public void update(Notification notification) throws RuntimeException {
        try {
            notificationDAO.update(notification);
            this.notify(RepositoryEvents.UPDATE, notification);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't update notification.");
        }
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        try {
            Notification notification = notificationDAO.readById(id);
            notificationDAO.delete(id);
            this.notify(RepositoryEvents.DELETE, notification);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't delete notification.");
        }
    }
}
