package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.ConfirmationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterConfirmMsgSentState extends UserPresenterViewState {
    private final ConfirmationDialog view = new ConfirmationDialog("Mensagem enviada");
    private final User user;

    public UserPresenterConfirmMsgSentState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getConfirmBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmMsgSent();
            }
        });
    }

    public void confirmMsgSent() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    @Override
    public JInternalFrame getView() {
        return view;
    }
}
