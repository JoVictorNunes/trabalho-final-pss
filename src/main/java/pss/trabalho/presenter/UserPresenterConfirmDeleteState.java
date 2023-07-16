package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.UserDeletedConfirmationDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterConfirmDeleteState extends UserPresenterViewState {
    private final UserDeletedConfirmationDialog view = new UserDeletedConfirmationDialog();

    public UserPresenterConfirmDeleteState(UserPresenter userPresenter, User user) {
        super(userPresenter);

        view.getUserNameTxt().setText(user.getName());
        view.getOkBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDelete();
            }
        });
    }

    @Override
    public void confirmDelete() {
        userPresenter.setUserPresenterViewState(new UserPresenterListState(userPresenter));
    }

    public UserDeletedConfirmationDialog getView() {
        return view;
    }
}
