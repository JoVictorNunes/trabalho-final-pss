package pss.trabalho.dao;

import pss.trabalho.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    void create(User user) throws RuntimeException;
    List<User> readAll() throws RuntimeException;
    User readById(UUID id) throws RuntimeException;
    void update(User user) throws RuntimeException;
    void delete(UUID id) throws RuntimeException;
}
