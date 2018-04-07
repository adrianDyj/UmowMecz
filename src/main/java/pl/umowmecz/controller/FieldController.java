package pl.umowmecz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.umowmecz.model.Field;
import pl.umowmecz.service.FieldService;

import java.util.List;

@Controller
@RequestMapping("/fields")
public class FieldController {

    private FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String fieldList(Model model) {
        List<Field> fields = fieldService.findAll();
        model.addAttribute("fieldList", fields);
        return "field_list";
    }
}
