package pss.trabalho.presenter;

import pss.trabalho.CurrentUser;
import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.exceptions.InvalidNameException;
import pss.trabalho.exceptions.InvalidPasswordException;
import pss.trabalho.model.User;
import pss.trabalho.view.SignUpView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPresenter extends AppPresenterState {
    private final SignUpView view;

    public SignUpPresenter(AppPresenter appPresenter) {
        super(appPresenter);
        view = new SignUpView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);

        view.getSignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
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
    public void signUp() {
        String name = view.getNameTxt().getText();
        String password = view.getPasswordTxt().getText();
        String passwordConfirmation = view.getPasswordConfirmationTxt().getText();

        try {
            User user = appPresenter.getUserService().signUp(name, password, passwordConfirmation);
            JOptionPane.showMessageDialog(view, "Cadastrado com sucesso!");
            CurrentUser.setInstance(user);
            view.setVisible(false);
            appPresenter.setState(new LoginPresenter(appPresenter));
        } catch (InvalidPasswordException | DuplicatedException | InvalidNameException e) {
            view.getErrorTxt().setText(e.getMessage());
        }
    }

    @Override
    public void cancel() {
        view.setVisible(false);
        appPresenter.setState(new LoginPresenter(appPresenter));
    }
}
