package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.CreateUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterUpdateState extends UserPresenterViewState {

    private final CreateUserView view = new CreateUserView();
    private final User user;

    public UserPresenterUpdateState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getNameTxt().setText(user.getName());
        view.getPasswordTxt().setText(user.getPassword());
        view.getPasswordConfirmationTxt().setText(user.getPassword());

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
        String name = view.getNameTxt().getText();
        String password = view.getPasswordTxt().getText();
        String passwordConfirmation = view.getPasswordConfirmationTxt().getText();

        user.setName(name);
        user.setPassword(password);
        userPresenter.setUserPresenterViewState(new UserPresenterConfirmUpdateState(userPresenter, user));
    }

    @Override
    public void cancel() {
        userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, user));
    }

    public CreateUserView getView() {
        return view;
    }
}
