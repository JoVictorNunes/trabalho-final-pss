package pss.trabalho.presenter;

import pss.trabalho.CurrentUser;
import pss.trabalho.model.User;
import pss.trabalho.view.UserDetailsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterDetailsState extends UserPresenterViewState {
    private final UserDetailsView view = new UserDetailsView();
    private final User user;

    public UserPresenterDetailsState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getDeleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });

        view.getDeleteBtn().setEnabled(!user.getId().equals(CurrentUser.getInstance().getId()));
        view.getAuthBtn().setEnabled(!user.getId().equals(CurrentUser.getInstance().getId()));
        view.getEditBtn().setEnabled(!user.getId().equals(CurrentUser.getInstance().getId()));
        view.getSendMsgBtn().setEnabled(!user.getId().equals(CurrentUser.getInstance().getId()));

        view.getEditBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

        view.getCloseBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        view.getSendMsgBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });

        view.getAuthBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auth();
            }
        });

        view.getAuthBtn().setEnabled(!user.isAuthorized());

        view.getUserNameTxt().setText(user.getName());
    }

    public void sendMsg() {
        userPresenter.setUserPresenterViewState(new UserPresenterSendNotificationState(userPresenter, user));
    }

    @Override
    public void delete() {
        userPresenter.setUserPresenterViewState(new UserPresenterDeleteState(userPresenter, user));
    }

    @Override
    public void edit() {
        userPresenter.setUserPresenterViewState(new UserPresenterUpdateState(userPresenter, user));
    }

    public void auth() {
        userPresenter.setUserPresenterViewState(new UserPresenterConfirmAuthState(userPresenter, user));
    }

    @Override
    public void close() {
        userPresenter.setUserPresenterViewState(new UserPresenterListState(userPresenter));
    }

    public UserDetailsView getView() {
        return view;
    }
}
