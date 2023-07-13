package pss.trabalho.presenter;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import pss.trabalho.view.PrincipalView;

/**
 *
 * @author Marcelo Augusto
 */
public class PrincipalPresenter {

    private PrincipalView view;
    private ArrayList<JInternalFrame> janelasInternas;
    private IncluirUsuarioPresenter teste1;

    public PrincipalPresenter() {
        view = new PrincipalView();
        janelasInternas = new ArrayList<>();
        addInternalFrame();
        configureScreen();
    }

    private void configureScreen() {

        this.view.setVisible(true);
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void addInternalFrame() {
        teste1 = IncluirUsuarioPresenter.getInstance();
        janelasInternas.add(teste1.getView());

        janelasInternas.forEach(janela -> {
            view.getjDesktop().add(janela);
//            view.add(janela);
        });
    }
}
