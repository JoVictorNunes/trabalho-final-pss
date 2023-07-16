package pss.trabalho.presenter;

import pss.trabalho.CurrentUser;
import pss.trabalho.CurrentUserObserver;
import pss.trabalho.model.User;
import pss.trabalho.service.UserService;

public class AppPresenter implements CurrentUserObserver {
    private LoginPresenter loginPresenter;
    private PrincipalPresenter principalPresenter;
    private AppPresenterState state;

    private UserService userService;

    public AppPresenter(UserService userService) {
        this.userService = userService;
        state = new LoginPresenter(this);
    }

    @Override
    public void onCurrentUserChange(User user) {

    }

    public void setState(AppPresenterState state) {
        this.state = state;
    }

    public UserService getUserService() {
        return userService;
    }
}
