package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pss.trabalho.view.IncluirUsuarioView;

public class addUserPresenter {

    private IncluirUsuarioView view;
    private static addUserPresenter instance = null;

    private addUserPresenter() {
        view = new IncluirUsuarioView();
        view.setSize(350, 300);
        view.setLocation(10, 10);
        view.setVisible(true);

        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(view, "Salvar");
            }
        });
    }

    public static addUserPresenter getInstance() {

        if (instance == null) {
            instance = new addUserPresenter();
        }
        return instance;

    }

    public IncluirUsuarioView getView() {
        return view;
    }

}
