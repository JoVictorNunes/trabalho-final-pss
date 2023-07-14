package pss.trabalho.presenter;

import pss.trabalho.view.components.ListaView;

/**
 *
 * @author Marcelo Augusto
 */
public class ListaNotificacoesPresenter {

    private ListaView view;
    private static ListaNotificacoesPresenter instance = null;

    private ListaNotificacoesPresenter() {
        view = new ListaView();
//        view.setSize(350, 300);
        view.setLocation(1080, 10);
        view.setVisible(true);

    }

    public static ListaNotificacoesPresenter getInstance() {
        if (instance == null) {
            instance = new ListaNotificacoesPresenter();

        }
        return instance;
    }

    public ListaView getView() {
        return view;

    }

}
