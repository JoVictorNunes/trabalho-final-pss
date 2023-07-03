package pass.trabalho.presenter;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import pss.trabalho.view.PrincipalView;
import pss.trabalho.view.TesteView;

/**
 *
 * @author Marcelo Augusto
 */
public class PrincipalPresenter {

    private PrincipalView view;
    private ArrayList<JInternalFrame> janelasInternas;
    private TesteView teste1;

    public PrincipalPresenter() {
        view = new PrincipalView();
        janelasInternas = new ArrayList<>();
        configureScreen();
    }

    private void configureScreen() {

        this.view.setVisible(true);
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.view.setLocationRelativeTo(view);

    }

    private void addInternalFrame() {
        
        janelasInternas.add(teste1);
        janelasInternas.forEach(janela ->{
        janela.setVisible(true);
        });
        

    }

}
