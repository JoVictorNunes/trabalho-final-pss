package pss.trabalho.repository;

import pss.trabalho.model.Notification;

public interface NotificationRepositoryObserver {
    void onNotificationRepositoryChange(RepositoryEvents event, Notification notification);
}
