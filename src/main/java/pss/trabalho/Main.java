package pss.trabalho;

import pss.trabalho.dao.NotificationDAO;
import pss.trabalho.dao.UserDAO;
import pss.trabalho.exceptions.DuplicatedException;
import pss.trabalho.model.User;
import pss.trabalho.repository.NotificationRepository;
import pss.trabalho.repository.UserRepository;
import pss.trabalho.repository.UserRepositoryObserver;
import pss.trabalho.service.UserService;
import pss.trabalho.presenter.PrincipalPresenter;
import java.util.List;
import pss.trabalho.presenter.LoginPresenter;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        UserRepository userRepository = new UserRepository(userDAO, notificationDAO);
        NotificationRepository notificationRepository = new NotificationRepository(notificationDAO);
        UserService userService = new UserService(userRepository, notificationRepository);
        UserRepositoryObserver observer = new UserRepositoryObserverTest();
        userRepository.registerObserver(observer);
        try {
            User user = userService.signIn("name", "password");
            System.out.println(CurrentUser.getInstance());
            List<User> users = userRepository.getAll();
            users.forEach(System.out::println);

            // ADICIONEI UMA TELA TESTE
            new PrincipalPresenter();
//            new LoginPresenter();
            //------------------------

        } catch (DuplicatedException e) {
            System.err.println(e.getMessage());
        }
    }
}
