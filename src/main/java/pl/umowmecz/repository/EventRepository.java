package pl.umowmecz.repository;

import org.springframework.data.repository.CrudRepository;
import pl.umowmecz.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
