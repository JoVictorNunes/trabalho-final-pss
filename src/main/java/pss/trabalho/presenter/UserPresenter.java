package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.repository.IUserRepository;
import pss.trabalho.repository.RepositoryEvents;
import pss.trabalho.repository.UserRepositoryObserver;
import pss.trabalho.service.IUserService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserPresenter implements UserRepositoryObserver {
    private final List<User> users;
    private UserPresenterViewState userPresenterViewState;
    private final List<ViewObserver> observers = new ArrayList<>();
    private final IUserService userService;

    public UserPresenter(IUserService userService) {
        this.userService = userService;
        users = userService.getUserRepository().getAll();
        userPresenterViewState = new UserPresenterListState(this);
        userService.getUserRepository().registerObserver(this);
    }

    public void setUserPresenterViewState(UserPresenterViewState userPresenterViewState) {
        this.userPresenterViewState = userPresenterViewState;
        for (ViewObserver observer : observers) {
            observer.onViewChange(userPresenterViewState.getView());
        }
    }

    public void registerViewObserver(ViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void onUserRepositoryChange(RepositoryEvents event, User user) {
        switch (event) {
            case CREATE:
                users.add(user);
                break;
            case DELETE:
                users.removeIf(u -> u.getId().equals(user.getId()));
                break;
            case UPDATE:
                users.removeIf(u -> u.getId().equals(user.getId()));
                users.add(user);
                break;
            default:
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public JInternalFrame getView() {
        return userPresenterViewState.getView();
    }

    public IUserRepository getUserRepository() {
        return userService.getUserRepository();
    }

    public IUserService getUserService() {
        return userService;
    }
}
