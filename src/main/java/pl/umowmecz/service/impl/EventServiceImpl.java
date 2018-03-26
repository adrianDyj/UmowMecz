package pl.umowmecz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.umowmecz.model.Event;
import pl.umowmecz.repository.EventRepository;
import pl.umowmecz.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public Event findEvent(long id) {
        return null;
    }

    @Override
    public void save(Event event) {

    }

    @Override
    public void delete(long id) {

    }
}
