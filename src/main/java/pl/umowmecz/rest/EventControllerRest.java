package pl.umowmecz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.Event;
import pl.umowmecz.service.EventService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    private EventService eventService;

    @Autowired
    public EventControllerRest(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Event>> getEvents() {
        Collection<Event> events = this.eventService.findAll();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = null;
        event = this.eventService.findEvent(id);
        if(event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> addEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (event == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        this.eventService.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody @Valid Event event,
                                             BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || event == null) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Event currentEvent = this.eventService.findEvent(id);
        if (currentEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentEvent.setDescription(event.getDescription());
        currentEvent.setTitle(event.getTitle());
        currentEvent.setType(event.getType());
        this.eventService.save(currentEvent);
        return new ResponseEntity<>(currentEvent, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        Event event = this.eventService.findEvent(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
