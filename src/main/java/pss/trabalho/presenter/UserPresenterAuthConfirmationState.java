package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.ConfirmationDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterAuthConfirmationState extends UserPresenterViewState {
    private final ConfirmationDialog view = new ConfirmationDialog("Usuário autorizado!");
    private final User user;

    public UserPresenterAuthConfirmationState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;
        view.getConfirmBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAuth();
            }
        });
    }

    public void confirmAuth() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    @Override
    public ConfirmationDialog getView() {
        return view;
    }
}
