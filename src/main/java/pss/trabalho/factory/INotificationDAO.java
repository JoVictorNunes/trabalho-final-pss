package pss.trabalho.factory;

import pss.trabalho.model.Notification;

import java.util.List;
import java.util.UUID;

public interface INotificationDAO {
    void create(Notification notification) throws RuntimeException;
    List<Notification> readAll() throws RuntimeException;
    List<Notification> readByUserId(UUID userId) throws RuntimeException;
    Notification readById(UUID id) throws RuntimeException;
    void update(Notification notification) throws RuntimeException;
    void delete(UUID id) throws RuntimeException;
}
