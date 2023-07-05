package pss.trabalho.presenter;

import pss.trabalho.view.TesteView;

public class TestePresenter {

    private TesteView view;
    private static TestePresenter instance = null;

    private TestePresenter() {
        view = new TesteView();
        view.setSize(350, 300);
        view.setLocation(10, 330);
        view.setVisible(true);
    }

    public static TestePresenter getInstance() {

        if (instance == null) {
            instance = new TestePresenter();
        }
        return instance;

    }

    public TesteView getView() {
        return view;
    }

}
