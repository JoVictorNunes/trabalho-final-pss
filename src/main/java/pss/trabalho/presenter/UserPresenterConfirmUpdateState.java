package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.UserUpdatedConfirmationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterConfirmUpdateState extends UserPresenterViewState {
    private final UserUpdatedConfirmationDialog view =  new UserUpdatedConfirmationDialog();
    private final User user;

    public UserPresenterConfirmUpdateState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getUserNameTxt().setText(user.getName());

        view.getConfirmBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmUpdate();
            }
        });
    }

    @Override
    public void confirmUpdate() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    public UserUpdatedConfirmationDialog getView() {
        return view;
    }
}
