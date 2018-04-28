package pl.umowmecz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umowmecz.model.Event;
import pl.umowmecz.model.User;
import pl.umowmecz.repository.EventRepository;
import pl.umowmecz.security.LoggedUser;
import pl.umowmecz.service.EventService;

import java.util.Date;
import java.util.List;

@Service
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
    public boolean save(Event event) {
        User user = LoggedUser.getLoggedUser();
        if (user != null) {
            if (checkIfUserCanAddMoreEvents(user)) {
                event.setUser(user);
                event.setPostedAt(new Date());
                eventRepository.save(event);
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(long id) {

    }

    private boolean checkIfUserCanAddMoreEvents(User user) {
        List<Event> events = user.getEvents();
        if(events.size() >= 3) {
            return false;
        }
        return true;
    }
}
