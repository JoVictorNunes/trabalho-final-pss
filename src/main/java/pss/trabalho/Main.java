package pss.trabalho;

import com.ufes.logger.log.LogFactory;
import com.ufes.logger.service.LogService;
import pss.trabalho.config.AppConfig;
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

        LogService logService = new LogService(LogFactory.create(0, AppConfig.getInstance().get("LOG_OUTPUT")));
        UserService userService = new UserService(userRepository, notificationRepository, logService);

        new AppPresenter(userService);
    }
}
