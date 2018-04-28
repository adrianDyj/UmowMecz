package pl.umowmecz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.umowmecz.model.User;
import pl.umowmecz.repository.UserRepository;

public class LoggedUser {

    @Autowired
    private static UserRepository userRepository;

    public static User getLoggedUser() {
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user != null) {
            return user;
        }
        return null;
    }
}
