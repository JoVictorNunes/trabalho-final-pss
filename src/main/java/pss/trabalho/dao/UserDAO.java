package pss.trabalho.dao;

import pss.trabalho.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.*;

public class UserDAO implements IUserDAO {
    private final Connection connection;

    public UserDAO() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:dev.db");
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't initialize UserDAO.", e);
        }
    }

    private void initializeTable() throws SQLException {
        Statement s = connection.createStatement();
        s.executeUpdate("CREATE TABLE IF NOT EXISTS users (id TEXT, createdAt INTEGER, name TEXT, password TEXT, isAdmin INTEGER, isAuthorized INTEGER)");
    }

    @Override
    public void create(User user) throws RuntimeException {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "INSERT INTO users VALUES ('%s', %s, '%s', '%s', %s, %s)",
                    user.getId(),
                    user.getCreatedAt(),
                    user.getName(),
                    user.getPassword(),
                    user.isAdmin(),
                    user.isAuthorized()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public List<User> readAll() throws RuntimeException {
        try {
            ArrayList<User> users = new ArrayList<User>();
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User(
                        UUID.fromString(result.getString("id")),
                        result.getString("name"),
                        result.getString("password"),
                        result.getLong("createdAt"),
                        result.getBoolean("isAdmin"),
                        result.getBoolean("isAuthorized")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public User readById(UUID id) {
        try {
            User user;
            Statement s = connection.createStatement();
            String query = String.format("SELECT * FROM users WHERE id = '%s'", id);
            ResultSet result = s.executeQuery(query);
            result.next();
            user = new User(
                    UUID.fromString(result.getString("id")),
                    result.getString("name"),
                    result.getString("password"),
                    result.getLong("createdAt"),
                    result.getBoolean("isAdmin"),
                    result.getBoolean("isAuthorized")
            );
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void update(User user) {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "UPDATE users SET id = '%s', createdAt = %s, name = '%s', password = '%s', isAdmin = %s, isAuthorized = %s WHERE id = '%s'",
                    user.getId(),
                    user.getCreatedAt(),
                    user.getName(),
                    user.getPassword(),
                    user.isAdmin(),
                    user.isAuthorized(),
                    user.getId()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void delete(User user) {
        try {
            Statement s = connection.createStatement();
            String query = String.format(
                    "DELETE FROM users WHERE id = '%s'",
                    user.getId()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }
}
