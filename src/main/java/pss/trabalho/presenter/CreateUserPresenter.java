package pss.trabalho.presenter;

import pss.trabalho.view.CreateUserView;

public class CreateUserPresenter {

    private CreateUserView view;

    public CreateUserPresenter() {
        this.view = new CreateUserView();

        this.view.setVisible(true);
    }

}
