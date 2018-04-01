package pl.umowmecz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/loginform")
    public String loginForm() {
        return "login_form";
    }
}
