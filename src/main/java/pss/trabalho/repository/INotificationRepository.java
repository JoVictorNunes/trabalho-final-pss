package pss.trabalho.repository;

import pss.trabalho.model.Notification;

import java.util.UUID;

public interface INotificationRepository {
    Notification getById(UUID id);
    void update(Notification notification);
}
