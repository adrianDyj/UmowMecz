package pl.umowmecz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umowmecz.model.Event;
import pl.umowmecz.repository.EventRepository;
import pl.umowmecz.service.EventService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findEvent(long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void delete(long id) {

    }
}
