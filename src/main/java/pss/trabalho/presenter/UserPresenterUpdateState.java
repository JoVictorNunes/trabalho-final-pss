package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.EditUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterUpdateState extends UserPresenterViewState {

    private final EditUserView view = new EditUserView();
    private final User user;

    public UserPresenterUpdateState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getNameTxt().setText(user.getName());

        view.getSaveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        view.getCancelBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    @Override
    public void save() {
        try {
            String name = view.getNameTxt().getText();
            String password = String.valueOf(view.getPassTxt().getPassword());
            String newPass = String.valueOf(view.getNewPassTxt().getPassword());
            String newPassConfirmation = String.valueOf(view.getNewPassConfTxt().getPassword());

            userPresenter.getUserService().update(user.getId(), name, password, newPass, newPassConfirmation);
            userPresenter.setUserPresenterViewState(new UserPresenterConfirmUpdateState(userPresenter, user));
        } catch (RuntimeException e) {
            view.getErrorTxt().setText(e.getMessage());
        }
    }

    @Override
    public void cancel() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    public EditUserView getView() {
        return view;
    }
}
