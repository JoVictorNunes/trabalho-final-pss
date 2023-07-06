package pss.trabalho;

import pss.trabalho.model.User;
import pss.trabalho.repository.RepositoryEvents;
import pss.trabalho.repository.UserRepositoryObserver;

public class UserRepositoryObserverTest implements UserRepositoryObserver {
    @Override
    public void onUserRepositoryChange(RepositoryEvents event, User user) {
        System.out.printf("event=%s user:%s%n", event, user);
    }
}
