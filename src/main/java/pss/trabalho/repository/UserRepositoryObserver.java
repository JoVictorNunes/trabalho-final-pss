package pss.trabalho.repository;

import pss.trabalho.model.User;

public interface UserRepositoryObserver {
    void onUserRepositoryChange(RepositoryEvents event, User user);
}
