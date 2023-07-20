package pss.trabalho.presenter;

import pss.trabalho.CurrentUser;
import pss.trabalho.model.Notification;
import pss.trabalho.model.User;
import pss.trabalho.repository.NotificationRepositoryObserver;
import pss.trabalho.repository.RepositoryEvents;
import pss.trabalho.service.IUserService;
import pss.trabalho.view.NotificationListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NotificationListPresenter implements NotificationRepositoryObserver {
    
    private final List<Notification> notifications = new ArrayList<>();
    private final NotificationListView view;
    private final IUserService userService;
    
    public NotificationListPresenter(IUserService userService) {
        view = new NotificationListView();
        view.setLocation(1030, 10);
        view.setVisible(false);
        this.userService = userService;
        
        List<Notification> notifications = userService.getNotificationRepository().getAll();
        for (Notification notification : notifications) {
            if (notification.getTo().equals(CurrentUser.getInstance().getId())) {
                this.notifications.add(notification);
            }
        }
        updateList();
        
        userService.getNotificationRepository().registerObserver(this);
        
        view.getReadBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readMsg();
            }
        });
        
        view.getCloseBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
            }
        });
    }
    
    public NotificationListView getView() {
        return view;
    }
    
    public void readMsg() {
        int selectedIndex = view.getjListNotificacoes().getSelectedIndex();
        Notification selectedNotification = notifications.get(selectedIndex);
        userService.readNotification(selectedNotification.getId());
    }
    
    @Override
    public void onNotificationRepositoryChange(RepositoryEvents event, Notification notification) {
        switch (event) {
            case CREATE:
                if (notification.getTo().equals(CurrentUser.getInstance().getId())) {
                    notifications.add(notification);
                }
                break;
            case DELETE:
                notifications.removeIf(n -> n.getId().equals(notification.getId()));
                break;
            case UPDATE:
                notifications.removeIf(n -> n.getId().equals(notification.getId()));
                notifications.add(notification);
                break;
        }
        updateList();
    }
    
    private void updateList() {
        String[] strings = new String[notifications.size()];
        
        int i = 0;
        for (Notification notification : notifications) {
            strings[i] = notification.getMessage();
            if (notification.isRead()) {
                strings[i] += "✔";
            }
            i++;
        }
        
        view.getjListNotificacoes().setListData(strings);
    }
}
