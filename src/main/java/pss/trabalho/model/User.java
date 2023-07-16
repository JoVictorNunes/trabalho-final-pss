package pss.trabalho.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID id;
    private final long createdAt;
    private String name;
    private String password;
    private boolean isAdmin;
    private boolean isAuthorized;
    private List<Notification> notificationList = new ArrayList<>();

    public User(UUID id, String name, String password, long createdAt, boolean isAdmin, boolean isAuthorized) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.isAdmin = isAdmin;
        this.isAuthorized = isAuthorized;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public String toString() {
        return String.format("id=%s createdAt=%s name=%s password=%s isAdmin=%s isAuthorized=%s", id, createdAt, name, password, isAdmin, isAuthorized);
    }
}
