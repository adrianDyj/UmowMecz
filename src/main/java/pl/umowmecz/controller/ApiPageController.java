package pl.umowmecz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApiPageController {

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiTemplate() {
        return "api";
    }

}
