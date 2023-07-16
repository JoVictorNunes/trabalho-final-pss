package pss.trabalho.presenter;

import javax.swing.*;

public abstract class UserPresenterViewState {
    protected final UserPresenter userPresenter;

    public UserPresenterViewState(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    public void add() {
        throw new RuntimeException("Invalid action.");
    }

    public void view() {
        throw new RuntimeException("Invalid action.");
    }

    public void delete() {
        throw new RuntimeException("Invalid action.");
    }

    public void edit() {
        throw new RuntimeException("Invalid action.");
    }

    public void close() {
        throw new RuntimeException("Invalid action.");
    }

    public void save() {
        throw new RuntimeException("Invalid action.");
    }

    public void cancel() {
        throw new RuntimeException("Invalid action.");
    }

    public void acceptDelete() {
        throw new RuntimeException("Invalid action.");
    }

    public void declineDelete() {
        throw new RuntimeException("Invalid action.");
    }

    public void confirmDelete() {
        throw new RuntimeException("Invalid action.");
    }

    public void confirmUpdate() {
        throw new RuntimeException("Invalid action.");
    }

    public void confirmCreate() {
        throw new RuntimeException("Invalid action.");
    }

    public abstract JInternalFrame getView();
}
