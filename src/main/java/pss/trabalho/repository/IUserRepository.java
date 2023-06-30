package pss.trabalho.repository;

import pss.trabalho.model.User;

import java.util.List;

public interface IUserRepository {
    void create(User user);
    List<User> getAll();
    void update(User user);
}
