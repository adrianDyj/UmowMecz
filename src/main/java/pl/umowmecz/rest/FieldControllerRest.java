package pl.umowmecz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.umowmecz.model.Field;
import pl.umowmecz.service.FieldService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/fields")
public class FieldControllerRest {

    private FieldService fieldService;

    @Autowired
    public FieldControllerRest(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Field>> getFields() {
        Collection<Field> fields = this.fieldService.findAll();
        if (fields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Field> getField(@PathVariable Long id) {
        Field field = null;
        field = this.fieldService.findField(id);
        if (field == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Field> addField(@RequestBody @Valid Field field, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (field == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        this.fieldService.save(field);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Field> updateField(@PathVariable Long id, @RequestBody @Valid Field field,
                                             BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || field == null) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Field currentField = this.fieldService.findField(id);
        if (currentField == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentField.setCity(field.getCity());
        currentField.setEmail(field.getEmail());
        currentField.setNextTo(field.getNextTo());
        currentField.setOpeningHours(field.getOpeningHours());
        currentField.setPhoneNumber(field.getPhoneNumber());
        currentField.setStreet(field.getStreet());
        currentField.setUrl(field.getUrl());
        this.fieldService.save(currentField);
        return new ResponseEntity<>(currentField, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        Field field = this.fieldService.findField(id);
        if (field == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.fieldService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
