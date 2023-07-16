package pss.trabalho.presenter;

import pss.trabalho.exceptions.UnauthorizedException;
import pss.trabalho.model.User;
import pss.trabalho.view.SendNotificationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPresenterSendNotificationState extends UserPresenterViewState {
    private final SendNotificationView view = new SendNotificationView();
    private final User user;

    public UserPresenterSendNotificationState(UserPresenter userPresenter, User user) {
        super(userPresenter);
        this.user = user;

        view.getSendBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });

        view.getNameTxt().setText(user.getName());
    }

    private void sendMsg() {
        String msg = view.getMsgTxt().getText();
        System.out.println(msg);
        try {
            userPresenter.getUserService().sendNotification(msg, user.getId());
            userPresenter.setUserPresenterViewState(new UserPresenterConfirmMsgSentState(userPresenter, user));
        } catch (UnauthorizedException e) {

        }
    }

    @Override
    public SendNotificationView getView() {
        return view;
    }
}
