package pl.umowmecz.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.User;
import pl.umowmecz.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register_form";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult, Model model) {
        
        if (usernameExists(user.getUsername())) {
            model.addAttribute("userAlreadyExists", "Użytkownik z tą nazwą użytkownika już instnieje.");
            return "register_form";
        }

        if (emailExists(user.getEmail())) {
            model.addAttribute("userAlreadyExists", "Użytkownik z tym adresem email już istnieje.");
            return "register_form";
        }

        if(bindResult.hasErrors())
            return "register_form";
        else {
            userService.addWithDefaultRole(user);
            logger.info("User registered with username: " + user.getUsername());
            return "register_success";
        }
    }

    private boolean emailExists(String givenEmail) {
        boolean doesEmailExists = false;
        User user = userService.findByEmail(givenEmail);
        if (user != null) {
            doesEmailExists = true;
        }
        return doesEmailExists;
    }

    private boolean usernameExists(String username) {
        boolean doesUsernameExists = false;
        User user = userService.findByUsername(username);
        if (user != null) {
            doesUsernameExists = true;
        }
        return doesUsernameExists;
    }
}
