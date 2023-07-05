package pss.trabalho;

import pss.trabalho.dao.NotificationDAO;
import pss.trabalho.dao.UserDAO;
import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.model.User;
import pss.trabalho.repository.NotificationRepository;
import pss.trabalho.repository.UserRepository;
import pss.trabalho.service.UserService;
import pss.trabalho.presenter.PrincipalPresenter;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        UserRepository userRepository = new UserRepository(userDAO, notificationDAO);
        NotificationRepository notificationRepository = new NotificationRepository(notificationDAO);
        UserService userService = new UserService(userRepository, notificationRepository);
        try {
            User user = userService.signIn("John", "password");
            System.out.println(user);
            List<User> users = userDAO.readAll();
            users.forEach(System.out::println);

            // ADICIONEI UMA TELA TESTE
            new PrincipalPresenter();
            //------------------------


        } catch (DuplicatedException e) {
            System.err.println(e.getMessage());
        }
    }
}
