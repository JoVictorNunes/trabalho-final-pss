package pss.trabalho;

import pss.trabalho.dao.NotificationDAO;
import pss.trabalho.dao.UserDAO;
import pss.trabalho.presenter.AppPresenter;
import pss.trabalho.repository.NotificationRepository;
import pss.trabalho.repository.UserRepository;
import pss.trabalho.service.UserService;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        UserRepository userRepository = new UserRepository(userDAO, notificationDAO);
        NotificationRepository notificationRepository = new NotificationRepository(notificationDAO);
        UserService userService = new UserService(userRepository, notificationRepository);

        new AppPresenter(userService);
    }
}
