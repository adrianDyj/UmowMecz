package pl.umowmecz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.umowmecz.model.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
