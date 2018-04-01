package pl.umowmecz.service;

import pl.umowmecz.model.Field;

import java.util.List;

public interface FieldService {

    List<Field> findAll();

    Field findField(long id);

    void save(Field field);

    void delete(long id);
}
