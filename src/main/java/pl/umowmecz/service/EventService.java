package pl.umowmecz.service;

import pl.umowmecz.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findEvent(long id);

    void save(Event event);

    void delete(long id);
}
