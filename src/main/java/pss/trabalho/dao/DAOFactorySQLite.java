package pss.trabalho.dao;

import pss.trabalho.factory.IDAOFactory;
import pss.trabalho.factory.INotificationDAO;
import pss.trabalho.factory.IUserDAO;

public class DAOFactorySQLite implements IDAOFactory {
    @Override
    public IUserDAO createUserDAO() {
        return new UserDAOSQLite();
    }

    @Override
    public INotificationDAO createNotificationDAO() {
        return new NotificationDAOSQLite();
    }
}
