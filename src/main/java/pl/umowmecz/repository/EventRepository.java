package pl.umowmecz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.umowmecz.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
