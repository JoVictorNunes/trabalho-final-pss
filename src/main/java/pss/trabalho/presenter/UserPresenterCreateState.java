package pss.trabalho.presenter;

import pss.trabalho.model.User;
import pss.trabalho.view.CreateUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.UUID;

public class UserPresenterCreateState extends UserPresenterViewState {
    private final CreateUserView view = new CreateUserView();

    public UserPresenterCreateState(UserPresenter userPresenter) {
        super(userPresenter);

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
        User user = new User(UUID.randomUUID(), name, password, new Date().getTime(), false, false, 0);
        userPresenter.getUserRepository().create(user);
        userPresenter.setUserPresenterViewState(new UserPresenterConfirmCreateState(userPresenter, user));
    }

    @Override
    public void cancel() {
        userPresenter.setUserPresenterViewState(new UserPresenterListState(userPresenter));
    }

    @Override
    public CreateUserView getView() {
        return view;
    }
}
