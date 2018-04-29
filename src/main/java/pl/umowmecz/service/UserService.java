package pl.umowmecz.service;

import pl.umowmecz.model.User;

public interface UserService {

    User findByEmail(String email);

    void addWithDefaultRole(User user);
}
