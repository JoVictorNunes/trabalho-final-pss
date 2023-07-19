package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pss.trabalho.exceptions.InvalidPasswordException;
import pss.trabalho.exceptions.NotFoundException;
import pss.trabalho.exceptions.UnauthorizedException;
import pss.trabalho.model.User;
import pss.trabalho.view.LoginView;

public class LoginPresenter extends AppPresenterState {
    private final LoginView view;

    public LoginPresenter(AppPresenter appPresenter) {
        super(appPresenter);
        this.view = new LoginView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);

        view.getBtnEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });

        view.getSignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
    }

    @Override
    public void signIn() {
        String username = view.getTxtUsuario().getText();
        String password = String.valueOf(view.getTxtSenha().getPassword());
        try {
            User user = appPresenter.getUserService().signIn(username, password);
            view.setVisible(false);
            appPresenter.setState(new PrincipalPresenter(appPresenter));
        } catch (InvalidPasswordException | NotFoundException | UnauthorizedException e) {
            view.getErrorTxt().setText(e.getMessage());
        }
    }

    @Override
    public void signUp() {
        view.setVisible(false);
        appPresenter.setState(new SignUpPresenter(appPresenter));
    }
}
