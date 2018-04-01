package pl.umowmecz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umowmecz.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
