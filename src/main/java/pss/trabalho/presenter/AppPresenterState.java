package pss.trabalho.presenter;

import javax.swing.*;

public abstract class AppPresenterState {

    protected final AppPresenter appPresenter;

    public AppPresenterState(AppPresenter appPresenter) {
        this.appPresenter = appPresenter;
    }

    public void signUp() {
        throw new RuntimeException("Invalid operation");
    }

    public void signIn() {
        throw new RuntimeException("Invalid operation");
    }

    public void signOut() {
        throw new RuntimeException("Invalid operation");
    }
    
    public void cancel() {
        throw new RuntimeException("Invalid operation");
    }

//    public abstract JFrame getView();
}
