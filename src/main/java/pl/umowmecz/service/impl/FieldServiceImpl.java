package pl.umowmecz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.umowmecz.model.Field;
import pl.umowmecz.repository.FieldRepository;
import pl.umowmecz.service.FieldService;

import java.util.List;

public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field findField(long id) {
        return fieldRepository.findOne(id);
    }

    @Override
    public void save(Field field) {
        fieldRepository.save(field);
    }

    @Override
    public void delete(long id) {

    }
}
