package pl.umowmecz.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umowmecz.model.Field;
import pl.umowmecz.service.FieldService;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldControllerRest {

    private FieldService fieldService;

    @Autowired
    public FieldControllerRest(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Field> getFields() {
        return fieldService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Field getField(@PathVariable Long id) {
        return fieldService.findField(id);
    }
}
