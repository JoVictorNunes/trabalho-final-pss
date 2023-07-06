package pss.trabalho.repository;

import pss.trabalho.model.Notification;

import java.util.List;
import java.util.UUID;

public interface INotificationRepository {
    void create(Notification notification) throws RuntimeException;
    List<Notification> getAll() throws RuntimeException;
    Notification getById(UUID id) throws RuntimeException;
    void update(Notification notification) throws RuntimeException;
    void delete(UUID id) throws RuntimeException;
}
