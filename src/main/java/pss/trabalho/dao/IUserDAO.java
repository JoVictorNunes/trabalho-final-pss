package pss.trabalho.dao;

import pss.trabalho.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    void create(User user);
    List<User> readAll();
    User readById(UUID id);
    void update(User user);
    void delete(User user);
}
