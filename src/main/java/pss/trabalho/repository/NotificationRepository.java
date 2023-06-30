package pss.trabalho.repository;

import pss.trabalho.dao.INotificationDAO;
import pss.trabalho.model.Notification;

import java.util.UUID;

public class NotificationRepository implements INotificationRepository {
    private final INotificationDAO notificationDAO;

    public NotificationRepository(INotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public Notification getById(UUID id) {
        return notificationDAO.readById(id);
    }

    @Override
    public void update(Notification notification) {
        notificationDAO.update(notification);
    }
}
