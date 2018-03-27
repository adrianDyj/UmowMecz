package pl.umowmecz.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.Event;
import pl.umowmecz.service.EventService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    private EventService eventService;

    public EventControllerRest(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEvents(@RequestParam(defaultValue = "title") String orderBy) {
        List<Event> events = eventService.findAll();
        if ("title".equals(orderBy)) {
            events.sort(Comparator.comparing(Event::getTitle));
        }
        return events;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable Long id) {
        return eventService.findEvent(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEvent(@RequestBody Event event) {
        eventService.save(event);
    }
}
