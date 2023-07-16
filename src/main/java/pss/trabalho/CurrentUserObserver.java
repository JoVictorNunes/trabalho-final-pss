package pss.trabalho;

import pss.trabalho.model.User;

public interface CurrentUserObserver {
    void onCurrentUserChange(User user);
}
