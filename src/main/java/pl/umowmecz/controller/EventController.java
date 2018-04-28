package pl.umowmecz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.umowmecz.model.Event;
import pl.umowmecz.service.EventService;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String addEvent(Model model) {
        model.addAttribute("eventModel", new Event());
        return "add_event";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute Event eventModel, RedirectAttributes redirectAttr) {
        boolean isEventAdded = eventService.save(eventModel);
        if (isEventAdded) {
            return "redirect:/";
        }
        redirectAttr.addFlashAttribute("message", "Nie możesz dodać więcej niż 3 wydarzenia na raz!");
        return "redirect:/event";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String eventList(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("eventList", events);
        return "event_list";
    }
}
