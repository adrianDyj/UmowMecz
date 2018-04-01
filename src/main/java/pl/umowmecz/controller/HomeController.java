package pl.umowmecz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.umowmecz.model.Event;
import pl.umowmecz.service.EventService;

import java.util.List;

@Controller
public class HomeController {

    private EventService eventService;

    @Autowired
    public HomeController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("eventList", events);
        return "index";
    }
}
