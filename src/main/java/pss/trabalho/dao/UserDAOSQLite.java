package pss.trabalho.dao;

import pss.trabalho.factory.IUserDAO;
import pss.trabalho.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.*;

public class UserDAOSQLite implements IUserDAO {
    public UserDAOSQLite() throws RuntimeException {
        try {
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't initialize UserDAOSQLite.", e);
        }
    }

    private void initializeTable() throws SQLException {
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        s.executeUpdate("CREATE TABLE IF NOT EXISTS users (id TEXT, createdAt INTEGER, name TEXT, password TEXT, isAdmin INTEGER, isAuthorized INTEGER, logType INTEGER)");
    }

    @Override
    public void create(User user) throws RuntimeException {
        try {
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            String query = String.format(
                    "INSERT INTO users VALUES ('%s', %s, '%s', '%s', %s, %s, %s)",
                    user.getId(),
                    user.getCreatedAt(),
                    user.getName(),
                    user.getPassword(),
                    user.isAdmin(),
                    user.isAuthorized(),
                    user.getLogType()
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
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet result = s.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User(
                        UUID.fromString(result.getString("id")),
                        result.getString("name"),
                        result.getString("password"),
                        result.getLong("createdAt"),
                        result.getBoolean("isAdmin"),
                        result.getBoolean("isAuthorized"),
                        result.getShort("logType")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public User readById(UUID id) throws RuntimeException {
        try {
            User user;
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            String query = String.format("SELECT * FROM users WHERE id = '%s'", id);
            ResultSet result = s.executeQuery(query);
            result.next();
            user = new User(
                    UUID.fromString(result.getString("id")),
                    result.getString("name"),
                    result.getString("password"),
                    result.getLong("createdAt"),
                    result.getBoolean("isAdmin"),
                    result.getBoolean("isAuthorized"),
                    result.getShort("logType")
            );
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void update(User user) throws RuntimeException {
        try {
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            String query = String.format(
                    "UPDATE users SET id = '%s', createdAt = %s, name = '%s', password = '%s', isAdmin = %s, isAuthorized = %s, logType = %s WHERE id = '%s'",
                    user.getId(),
                    user.getCreatedAt(),
                    user.getName(),
                    user.getPassword(),
                    user.isAdmin(),
                    user.isAuthorized(),
                    user.getLogType(),
                    user.getId()
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        try {
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            String query = String.format(
                    "DELETE FROM users WHERE id = '%s'",
                    id
            );
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't access database.", e);
        }
    }
}
