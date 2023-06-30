package pss.trabalho.dao;

import pss.trabalho.model.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationDAO implements INotificationDAO {
    private final Connection connection;

    public NotificationDAO() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:dev.db");
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't initialize NotificationDAO.", e);
        }
    }

    private void initializeTable() throws SQLException {
        Statement s = connection.createStatement();
        s.executeUpdate("CREATE TABLE IF NOT EXISTS notifications (id TEXT, message TEXT, read INTEGER, userId TEXT)");
    }

    @Override
    public void create(Notification notification) {
        try {
            Statement s = connection.createStatement();
            String query = "INSERT INTO notifications VALUES ('" + notification.getId().toString() + "', '" + notification.getMessage() + "', " + notification.isRead() + ", '" + notification.getTo() + "')";
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't create notification.", e);
        }
    }

    @Override
    public List<Notification> readAll() {
        try {
            ArrayList<Notification> notifications = new ArrayList<Notification>();
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery("SELECT * FROM notifications");
            while (result.next()) {
                Notification n = new Notification(
                        UUID.fromString(result.getString("id")),
                        result.getString("message"),
                        UUID.fromString(result.getString("userId")),
                        result.getBoolean("read")
                );
                notifications.add(n);
            }
            return notifications;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public List<Notification> readByUserId(UUID userId) {
        try {
            ArrayList<Notification> notifications = new ArrayList<Notification>();
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery("SELECT * FROM notifications WHERE userId = '" + userId.toString() + "'");
            while (result.next()) {
                Notification n = new Notification(
                        UUID.fromString(result.getString("id")),
                        result.getString("message"),
                        UUID.fromString(result.getString("userId")),
                        result.getBoolean("read")
                );
                notifications.add(n);
            }
            return notifications;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public Notification readById(UUID id) {
        try {
            Notification notification;
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery("SELECT * FROM notifications WHERE id = '" + id.toString() + "'");
            result.next();
            notification = new Notification(
                    UUID.fromString(result.getString("id")),
                    result.getString("message"),
                    UUID.fromString(result.getString("userId")),
                    result.getBoolean("read")
            );
            return notification;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void update(Notification user) {

    }

    @Override
    public void delete(UUID id) {

    }
}
