package pl.umowmecz.service;

import pl.umowmecz.model.User;

public interface UserService {

    User findByEmail(String email);

    User findByUsername(String username);

    void addWithDefaultRole(User user);
}
