package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import pss.trabalho.model.User;
import pss.trabalho.repository.IUserRepository;
import pss.trabalho.view.MainView;

/**
 *
 * @author Marcelo Augusto
 */
public class PrincipalPresenter extends AppPresenterState implements ViewObserver {

    private MainView view;
    private ArrayList<JInternalFrame> janelasInternas;
    private addUserPresenter teste1;
    private ListaNotificacoesPresenter teste2;
    private final IUserRepository userRepository;
    private final UserPresenter userPresenter;
    private JInternalFrame userPresenterView;

    public PrincipalPresenter(User user, IUserRepository userRepository, AppPresenter appPresenter) {
        super(appPresenter);
        this.userRepository = userRepository;
        view = new MainView();
        this.userPresenter = new UserPresenter(userRepository);
        userPresenter.registerViewObserver(this);
        this.userPresenterView = userPresenter.getView();
        janelasInternas = new ArrayList<>();
        addInternalFrame();
        configureScreen();

        view.getMenuOpSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signOut();
            }
        });
    }

    private void configureScreen() {

        this.view.setVisible(true);

        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    @Override
    public void onViewChange(JInternalFrame userPresenterView) {
        this.userPresenterView.setVisible(false);
        view.getjDesktop().remove(this.userPresenterView);
        this.userPresenterView = userPresenterView;
        this.userPresenterView.setVisible(true);
        view.getjDesktop().add(this.userPresenterView);
    }

    private void addInternalFrame() {
        teste1 = addUserPresenter.getInstance();
        teste2 = ListaNotificacoesPresenter.getInstance();
        janelasInternas.add(teste1.getView());
        janelasInternas.add(teste2.getView());
        janelasInternas.add(userPresenterView);

        janelasInternas.forEach(janela -> {
            view.getjDesktop().add(janela);
//            view.add(janela);
        });
    }

    public MainView getView() {
        return view;
    }

    @Override
    public void signOut() {
        appPresenter.getUserService().signOut();
        appPresenter.setState(new LoginPresenter(appPresenter));
        view.setVisible(false);
    }
}
