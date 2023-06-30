package pss.trabalho.dao;

import pss.trabalho.model.Notification;

import java.util.List;
import java.util.UUID;

public interface INotificationDAO {
    void create(Notification notification);
    List<Notification> readAll();
    List<Notification> readByUserId(UUID userId);
    Notification readById(UUID id);
    void update(Notification notification);
    void delete(UUID id);
}
