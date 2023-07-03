package pass.trabalho.presenter;

import pss.trabalho.view.TesteView;

public class TestePresenter {

    private TesteView view;
    private static TestePresenter instance = null;

    private TestePresenter() {
        view = new TesteView();
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
