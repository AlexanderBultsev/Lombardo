package com.pristine.lombardo.controller;

import com.pristine.lombardo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;


    @GetMapping("")
    public String showPropertyList(Model model) {
        model.addAttribute("properties", propertyService.findAll());

        return "property-list";
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable Long id, Model model) {
        model.addAttribute("property", propertyService.findById(id));

        return "property";
    }

    @GetMapping("/{id}/del")
    public String delete(@PathVariable Long id) {
        propertyService.deleteById(id);

        return "redirect:/property";
    }
}
