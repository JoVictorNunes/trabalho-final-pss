package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.UserDeleteConfirmationDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterDeleteState extends UserPresenterViewState {
    private final UserDeleteConfirmationDialog view = new UserDeleteConfirmationDialog();
    private final User user;

    public UserPresenterDeleteState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getYesBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDelete();
            }
        });

        view.getNoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               declineDelete();
            }
        });
    }

    @Override
    public void confirmDelete() {
        userPresenter.getUserRepository().delete(user.getId());
        userPresenter.setUserPresenterViewState(new UserPresenterConfirmDeleteState(userPresenter, user));
    }

    @Override
    public void declineDelete() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    public UserDeleteConfirmationDialog getView() {
        return view;
    }
}
