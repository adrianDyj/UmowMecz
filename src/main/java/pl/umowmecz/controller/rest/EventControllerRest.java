package pl.umowmecz.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.Event;
import pl.umowmecz.repository.EventRepository;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    private EventRepository eventRepository;

    public EventControllerRest(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEvents(@RequestParam(defaultValue = "title") String orderBy) {
        List<Event> events = eventRepository.findAll();
        if ("title".equals(orderBy)) {
            events.sort(Comparator.comparing(Event::getTitle));
        }
        return events;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable Long id) {
        return eventRepository.findOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEvent(@RequestBody Event event) {
        eventRepository.save(event);
    }
}
