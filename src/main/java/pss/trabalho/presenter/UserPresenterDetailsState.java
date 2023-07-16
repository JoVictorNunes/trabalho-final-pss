package pss.trabalho.presenter;

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

        view.getUserNameTxt().setText(user.getName());
    }

    @Override
    public void delete() {
        userPresenter.setUserPresenterViewState(new UserPresenterDeleteState(userPresenter, user));
    }

    @Override
    public void edit() {
        userPresenter.setUserPresenterViewState(new UserPresenterUpdateState(userPresenter, user));
    }

    @Override
    public void close() {
        userPresenter.setUserPresenterViewState(new UserPresenterListState(userPresenter));
    }

    public UserDetailsView getView() {
        return view;
    }
}
