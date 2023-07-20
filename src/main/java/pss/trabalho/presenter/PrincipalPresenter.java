package pss.trabalho.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import pss.trabalho.CurrentUser;
import pss.trabalho.model.User;
import pss.trabalho.view.AccountView;
import pss.trabalho.view.ConfigView;
import pss.trabalho.view.MainView;
import pss.trabalho.view.NotificationListView;

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

        view.getConfigItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigView configView = new ConfigView();
                configView.getLogBox().setSelectedIndex(CurrentUser.getInstance().getLogType());
                configView.setVisible(true);

                configView.getSaveBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        configView.setVisible(false);
                        saveConfig((short) configView.getLogBox().getSelectedIndex());
                    }
                });

                view.getjDesktop().add(configView);
            }
        });

        view.getAccItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountView accountView = new AccountView();
                accountView.getNameTxt().setText(CurrentUser.getInstance().getName());
                accountView.setVisible(true);

                accountView.getSaveBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String oldPassword = String.valueOf(accountView.getOldPasswordTxt().getPassword());
                        String newPassword = String.valueOf(accountView.getNewPasswordTxt().getPassword());
                        String newPasswordConfirmation = String.valueOf(accountView.getNewPasswordConfirmationTxt().getPassword());

                        try {
                            appPresenter.getUserService().updatePassword(oldPassword, newPassword, newPasswordConfirmation);
                            JOptionPane.showMessageDialog(view, "Senha Alterada com sucesso");
                        } catch (RuntimeException exception) {
                            accountView.getErrorTxt().setText(exception.getMessage());
                        }
                    }
                });

                view.getjDesktop().add(accountView);
            }
        });

        view.getTxtTipo().setText(CurrentUser.getInstance().isAdmin() ? "Administrador" : "Usuário");
        view.getTxtUsuario().setText(CurrentUser.getInstance().getName());
        view.getBtnNotificacao().setText(String.valueOf(CurrentUser.getInstance().getNotificationList().size()));

        view.getBtnNotificacao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotificationListView notifications = new NotificationListPresenter(appPresenter.getUserService()).getView();
                notifications.setVisible(true);
                view.getjDesktop().add(notifications);

            }
        });
    }

    public void saveConfig(short logType) {
        appPresenter.getUserService().changeLogType(logType);
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
