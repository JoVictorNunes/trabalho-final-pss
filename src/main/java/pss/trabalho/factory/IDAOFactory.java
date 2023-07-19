package pss.trabalho.factory;

public interface IDAOFactory {
    IUserDAO createUserDAO();
    INotificationDAO createNotificationDAO();
}
