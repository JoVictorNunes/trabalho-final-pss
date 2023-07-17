package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.OptionDialogView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterConfirmAuthState extends UserPresenterViewState {
    private final OptionDialogView view = new OptionDialogView();
    private final User user;

    public UserPresenterConfirmAuthState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getMsgTxt().setText("Autorizar usuário " + user.getName() + "?");

        view.getYesBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptAuth();
            }
        });

        view.getNoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declineAuth();
            }
        });
    }

    public void acceptAuth() {
        userPresenter.getUserService().authorizeUser(user.getId());
        userPresenter.setUserPresenterViewState(new UserPresenterAuthConfirmationState(userPresenter, user));
    }

    public void declineAuth() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    @Override
    public OptionDialogView getView() {
        return view;
    }
}
