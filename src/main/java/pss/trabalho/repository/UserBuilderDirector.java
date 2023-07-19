package pss.trabalho.repository;

import pss.trabalho.model.User;

public class UserBuilderDirector {
    public User build(UserBuilder builder) {
        builder.addUser();
        builder.addNotifications();
        return builder.getUser();
    }
}
