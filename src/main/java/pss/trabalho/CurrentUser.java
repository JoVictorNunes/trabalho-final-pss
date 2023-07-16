package pss.trabalho;

import pss.trabalho.model.User;

public class CurrentUser {
    private static User user;
    public static User getInstance() {
        return CurrentUser.user;
    }

    public static void setInstance(User user) {
        CurrentUser.user = user;
    }
}
