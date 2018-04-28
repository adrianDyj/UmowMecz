package pl.umowmecz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.umowmecz.model.Event;
import pl.umowmecz.model.User;
import pl.umowmecz.repository.EventRepository;
import pl.umowmecz.repository.UserRepository;
import pl.umowmecz.service.EventService;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

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
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user != null) {
            event.setUser(user);
        }
        event.setPostedAt(new Date());
        eventRepository.save(event);
    }

    @Override
    public void delete(long id) {

    }
}
