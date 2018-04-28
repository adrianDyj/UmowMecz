package pl.umowmecz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.umowmecz.model.User;
import pl.umowmecz.repository.UserRepository;

@Component
public class LoggedUser {
    
    private static UserRepository userRepository;

    @Autowired
    public LoggedUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
