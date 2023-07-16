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
    public void create(Notification notification) throws RuntimeException {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "INSERT INTO notifications VALUES ('%s', '%s', %s, '%s')",
                    notification.getId(),
                    notification.getMessage(),
                    notification.isRead(),
                    notification.getTo()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public List<Notification> readAll() throws RuntimeException {
        try {
            ArrayList<Notification> notifications = new ArrayList<>();
            Statement s = connection.createStatement();
            String query = "SELECT * FROM notifications";
            ResultSet result = s.executeQuery(query);
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
    public List<Notification> readByUserId(UUID userId) throws RuntimeException {
        try {
            ArrayList<Notification> notifications = new ArrayList<>();
            Statement s = connection.createStatement();
            String query = String.format("SELECT * FROM notifications WHERE userId = '%s'", userId);
            ResultSet result = s.executeQuery(query);
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
    public Notification readById(UUID id) throws RuntimeException {
        try {
            Notification notification;
            Statement s = connection.createStatement();
            String query = String.format("SELECT * FROM notifications WHERE id = '%s'", id);
            ResultSet result = s.executeQuery(query);
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
    public void update(Notification notification) throws RuntimeException {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "UPDATE notifications SET id = '%s', message = '%s', read = %s, userId = '%s' WHERE id = '%s'",
                    notification.getId(),
                    notification.getMessage(),
                    notification.isRead(),
                    notification.getTo(),
                    notification.getId()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "DELETE FROM notifications WHERE id = '%s'",
                    id
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }
}
