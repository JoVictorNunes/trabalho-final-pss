package pss.trabalho.presenter;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import pss.trabalho.model.User;
import pss.trabalho.view.MainView;

/**
 *
 * @author Marcelo Augusto
 */
public class PrincipalPresenter {

    private MainView view;
    private ArrayList<JInternalFrame> janelasInternas;
    private addUserPresenter teste1;
    private ListaNotificacoesPresenter teste2;

    public PrincipalPresenter(User user) {
        view = new MainView();
        janelasInternas = new ArrayList<>();
        addInternalFrame();
        configureScreen();
    }

    private void configureScreen() {

        this.view.setVisible(true);

        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void addInternalFrame() {
        teste1 = addUserPresenter.getInstance();
        teste2 = ListaNotificacoesPresenter.getInstance();
        janelasInternas.add(teste1.getView());
        janelasInternas.add(teste2.getView());

        janelasInternas.forEach(janela -> {
            view.getjDesktop().add(janela);
//            view.add(janela);
        });
    }
}
