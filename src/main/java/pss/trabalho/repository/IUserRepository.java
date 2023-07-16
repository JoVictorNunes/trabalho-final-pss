package pss.trabalho.repository;

import pss.trabalho.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    void create(User user) throws RuntimeException;
    List<User> getAll() throws RuntimeException;
    User getById(UUID id) throws RuntimeException;
    void update(User user) throws RuntimeException;
    void delete(UUID id) throws RuntimeException;
    void registerObserver(UserRepositoryObserver observer);
}
