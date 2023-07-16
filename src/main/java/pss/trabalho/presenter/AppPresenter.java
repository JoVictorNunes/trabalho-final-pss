package pss.trabalho.presenter;

import pss.trabalho.service.UserService;

public class AppPresenter {
    private AppPresenterState state;
    private final UserService userService;

    public AppPresenter(UserService userService) {
        this.userService = userService;
        state = new LoginPresenter(this);
    }

    public void setState(AppPresenterState state) {
        this.state = state;
    }

    public UserService getUserService() {
        return userService;
    }
}
