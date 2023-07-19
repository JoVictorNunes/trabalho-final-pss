package pss.trabalho;

import com.ufes.logger.log.LogFactory;
import com.ufes.logger.service.LogService;
import pss.trabalho.config.AppConfig;
import pss.trabalho.dao.DAOFactorySQLite;
import pss.trabalho.factory.IDAOFactory;
import pss.trabalho.factory.INotificationDAO;
import pss.trabalho.factory.IUserDAO;
import pss.trabalho.presenter.AppPresenter;
import pss.trabalho.repository.INotificationRepository;
import pss.trabalho.repository.IUserRepository;
import pss.trabalho.repository.NotificationRepository;
import pss.trabalho.repository.UserRepository;
import pss.trabalho.service.UserService;

public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = new DAOFactorySQLite();
        IUserDAO userDAOSQLite = factory.createUserDAO();
        INotificationDAO notificationDAOSQLite = factory.createNotificationDAO();
        IUserRepository userRepository = new UserRepository(userDAOSQLite, notificationDAOSQLite);
        INotificationRepository notificationRepository = new NotificationRepository(notificationDAOSQLite);

        LogService logService = new LogService(LogFactory.create(0, AppConfig.getInstance().get("LOG_OUTPUT")));
        UserService userService = new UserService(userRepository, notificationRepository, logService);

        new AppPresenter(userService);
    }
}
