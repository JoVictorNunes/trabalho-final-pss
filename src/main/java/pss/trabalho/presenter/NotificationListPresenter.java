package pss.trabalho.presenter;

import pss.trabalho.view.components.ListaView;

/**
 *
 * @author Marcelo Augusto
 */
public class NotificationListPresenter {

    private ListaView view;
    private static NotificationListPresenter instance = null;

    private NotificationListPresenter() {
        view = new ListaView();
//        view.setSize(350, 300);
        view.setLocation(1080, 10);
        view.setVisible(true);

    }

    public static NotificationListPresenter getInstance() {
        if (instance == null) {
            instance = new NotificationListPresenter();

        }
        return instance;
    }

    public ListaView getView() {
        return view;

    }

}
