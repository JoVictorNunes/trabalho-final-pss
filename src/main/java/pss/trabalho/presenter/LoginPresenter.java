package pss.trabalho.presenter;

import pss.trabalho.view.LoginView;

/**
 *
 * @author Marcelo Augusto
 */
public class LoginPresenter {
    
    private LoginView view;
    
    public LoginPresenter() {
        this.view = new LoginView();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
    
}
