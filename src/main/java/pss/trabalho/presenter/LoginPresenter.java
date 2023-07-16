package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import pss.trabalho.dao.NotificationDAO;
import pss.trabalho.dao.UserDAO;
import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.model.User;
import pss.trabalho.repository.UserRepository;
import pss.trabalho.view.LoginView;
import com.pss.senha.validacao.ValidadorSenha;

/**
 *
 * @author Marcelo Augusto
 */
public class LoginPresenter extends AppPresenterState {

    private LoginView view;

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
    }

    public LoginView getView() {
        return view;
    }

    @Override
    public void signIn() {
        String username = view.getTxtUsuario().getText();
        String password = String.valueOf(view.getTxtSenha().getPassword());
        try {
            ValidadorSenha validadorSenha = new ValidadorSenha();
            List<String> result = validadorSenha.validar(password);
            if (result.isEmpty()) {
                System.out.println("hm");
                User user = appPresenter.getUserService().signIn(username, password);
                view.setVisible(false);
                appPresenter.setState(new PrincipalPresenter(user, new UserRepository(new UserDAO(), new NotificationDAO()), appPresenter));
                return;
            }
            System.out.println(result);
        } catch (DuplicatedException e) {
            System.out.println(e);
        }
    }
}
