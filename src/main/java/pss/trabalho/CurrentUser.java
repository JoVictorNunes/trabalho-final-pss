package pss.trabalho;

import pss.trabalho.model.User;

import java.util.ArrayList;
import java.util.List;

public class CurrentUser {
    private static User user;
    private static final List<CurrentUserObserver> observers = new ArrayList<>();

    public static User getInstance() {
        return CurrentUser.user;
    }

    public static void setInstance(User user) {
        CurrentUser.user = user;
        for (CurrentUserObserver observer : observers) {
            observer.onCurrentUserChange(user);
        }
    }

    public static void registerObserver(CurrentUserObserver observer) {
        observers.add(observer);
    }
}
