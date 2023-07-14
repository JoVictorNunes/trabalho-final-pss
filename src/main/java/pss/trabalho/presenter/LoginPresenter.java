package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.model.User;
import pss.trabalho.service.UserService;
import pss.trabalho.view.LoginView;

/**
 *
 * @author Marcelo Augusto
 */
public class LoginPresenter {

    private LoginView view;

    public LoginPresenter() {
        this.view = new LoginView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.getBtnEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nomeUsuario = view.getTxtUsuario().getText();
                String senha = String.valueOf(view.getTxtSenha().getPassword());

                //verificação do login aqui 
                JOptionPane.showMessageDialog(view, "Usuário:" + nomeUsuario + senha);
                //printei o nome e senha pra ver o que os campos está pegando 
                try {
                    User UserLogado = new UserService().signIn(nomeUsuario, senha);
                    new PrincipalPresenter(UserLogado);
                } catch (DuplicatedException ex) {
                    Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.dispose();
            }
        });
    }

}
