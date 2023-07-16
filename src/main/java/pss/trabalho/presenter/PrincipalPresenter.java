package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import pss.trabalho.CurrentUser;
import pss.trabalho.model.User;
import pss.trabalho.view.MainView;

public class PrincipalPresenter extends AppPresenterState implements ViewObserver {
    private final MainView view;
    private JInternalFrame userPresenterView;

    public PrincipalPresenter(AppPresenter appPresenter) {
        super(appPresenter);
        view = new MainView();
        addInternalFrame();
        configureScreen();

        view.getMenuOpSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signOut();
            }
        });
    }

    private void configureScreen() {
        this.view.setVisible(true);
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void onViewChange(JInternalFrame userPresenterView) {
        this.userPresenterView.setVisible(false);
        view.getjDesktop().remove(this.userPresenterView);
        this.userPresenterView = userPresenterView;
        this.userPresenterView.setVisible(true);
        view.getjDesktop().add(this.userPresenterView);
    }

    private void addInternalFrame() {
        User currentUser = CurrentUser.getInstance();
        ArrayList<JInternalFrame> innerWindows = new ArrayList<>();
        innerWindows.add(new NotificationListPresenter(appPresenter.getUserService()).getView());

        if (currentUser.isAdmin()) {
            UserPresenter userPresenter = new UserPresenter(appPresenter.getUserService());
            userPresenter.registerViewObserver(this);
            innerWindows.add(userPresenter.getView());
            this.userPresenterView = userPresenter.getView();
        }

        innerWindows.forEach(window -> {
            view.getjDesktop().add(window);
        });
    }

    public MainView getView() {
        return view;
    }

    @Override
    public void signOut() {
        appPresenter.getUserService().signOut();
        appPresenter.setState(new LoginPresenter(appPresenter));
        view.setVisible(false);
    }
}
