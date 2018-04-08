package pl.umowmecz.rest;

import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fields")
public class FieldControllerRest {

    private final String gd = "gdańsk";
    private final String ga = "gdynia";
    private final String gsp = "sopot";

    Logger logger = Logger.getLogger(FieldControllerRest.class);
    private FieldService fieldService;

    @Autowired
    public FieldControllerRest(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Field>> getFields(@RequestParam(required=false) String city) {
        Collection<Field> fields = this.fieldService.findAll();
        if (fields.isEmpty()) {
            logger.info("No fields found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (city != null) {
            if (city.equalsIgnoreCase("gdansk") || city.equalsIgnoreCase(gd)) {
                fields = fields.stream().filter(x -> x.getCity().equalsIgnoreCase(gd)).collect(Collectors.toList());
            } else if (city.equalsIgnoreCase(ga)) {
                fields = fields.stream().filter(x -> x.getCity().equalsIgnoreCase(ga)).collect(Collectors.toList());
            } else if (city.equalsIgnoreCase(gsp)) {
                fields = fields.stream().filter(x -> x.getCity().equalsIgnoreCase(gsp)).collect(Collectors.toList());
            } else {
                logger.info("No field found in city: " + city.toString());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        logger.info("Found " + fields.stream().count() + " field/s");
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Field> getField(@PathVariable Long id) {
        Field field = null;
        field = this.fieldService.findField(id);
        if (field == null) {
            logger.info("No field found with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found field with id: " + id);
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
        logger.info("Field with id " + id + " has been updated");
        this.fieldService.save(currentField);
        return new ResponseEntity<>(currentField, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        Field field = this.fieldService.findField(id);
        if (field == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Field with id " + id + " has been deleted");
        this.fieldService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
