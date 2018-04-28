package pl.umowmecz.service;

import pl.umowmecz.model.User;

public interface UserService {

    User findUser(long id);

    void addWithDefaultRole(User user);
}
