package pss.trabalho.presenter;

import pss.trabalho.model.Notification;
import pss.trabalho.model.User;
import pss.trabalho.view.UserListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPresenterListState extends UserPresenterViewState {
    private final UserListView view = new UserListView();

    public UserPresenterListState(UserPresenter userPresenter) {
        super(userPresenter);

        // Initialize users table
        List<User> users = userPresenter.getUsers();
        String[][] d = new String[users.size()][4];

        int i = 0;
        for (User user : users) {
            System.out.println(user);
            if (user != null) {
                int notificationsRead = 0;
                for (Notification n : user.getNotificationList()) {
                    if (n.isRead()) notificationsRead++;
                }
                d[i] = new String[]{user.getName(), String.valueOf(user.getCreatedAt()), String.valueOf(user.getNotificationList().size()), String.valueOf(notificationsRead)};
                i++;
            }
        }

        view.getUserTable().setModel(new javax.swing.table.DefaultTableModel(
                d,
                new String[]{
                        "Nome", "Cadastrado em", "Notificações enviadas", "Notificações lidas"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        view.getNewUserBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        view.getViewUserBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view();
            }
        });
        view.setVisible(true);
    }

    @Override
    public void add() {
        this.userPresenter.setUserPresenterViewState(new UserPresenterCreateState(userPresenter));
    }

    @Override
    public void view() {
        int selectedRow = view.getUserTable().getSelectedRow();
        User selectedUser = userPresenter.getUsers().get(selectedRow);
        this.userPresenter.setUserPresenterViewState(new UserPresenterDetailsState(userPresenter, selectedUser));
    }

    public UserListView getView() {
        return view;
    }
}
