package pss.trabalho.presenter;

import pss.trabalho.view.IncluirUsuarioView;

public class IncluirUsuarioPresenter {

    private IncluirUsuarioView view;
    private static IncluirUsuarioPresenter instance = null;

    private IncluirUsuarioPresenter() {
        view = new IncluirUsuarioView();
        view.setSize(350, 300);
        view.setLocation(10, 10);
        view.setVisible(true);
    }

    public static IncluirUsuarioPresenter getInstance() {

        if (instance == null) {
            instance = new IncluirUsuarioPresenter();
        }
        return instance;

    }

    public IncluirUsuarioView getView() {
        return view;
    }

}
