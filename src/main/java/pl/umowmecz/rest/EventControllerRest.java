package pl.umowmecz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.Event;
import pl.umowmecz.service.EventService;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    private EventService eventService;

    @Autowired
    public EventControllerRest(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEvents(@RequestParam(defaultValue = "id") String orderBy) {
        List<Event> events = eventService.findAll();
        if ("id".equals(orderBy)) {
            events.sort(Comparator.comparing(Event::getId));
        }
        return events;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable Long id, HttpServletResponse response) {
        Event event = eventService.findEvent(id);
        if(event == null) {
            response.setStatus(404);
        }
        return event;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEvent(@RequestBody Event event) {
        eventService.save(event);
    }
}
