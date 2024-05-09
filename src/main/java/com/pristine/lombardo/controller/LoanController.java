package com.pristine.lombardo.controller;

import com.pristine.lombardo.addition.LoanStatus;
import com.pristine.lombardo.dto.LoanDTO;
import com.pristine.lombardo.dto.PropertyDTO;
import com.pristine.lombardo.entity.Loan;
import com.pristine.lombardo.entity.Property;
import com.pristine.lombardo.service.LoanService;
import com.pristine.lombardo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    PropertyService propertyService;


    @PostMapping("/add")
    public String add(PropertyDTO propertyDTO) {
        Property property = propertyService.createProperty(propertyDTO);

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setClientId(propertyDTO.getClientId());
        loanDTO.setPropertyId(property.getId());

        Loan loan = loanService.createLoan(loanDTO);

        return "redirect:/loan/" + loan.getId();
    }


    @GetMapping("")
    public String showLoanList(Model model, @RequestParam(required=false) LoanStatus status) {
        if (status != null) {
            model.addAttribute("loans", loanService.findByStatus(status));
        } else {
            model.addAttribute("loans", loanService.findAll());
        }
        return "loan-list";
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable Long id, Model model) {
        model.addAttribute("loan", loanService.findById(id));

        return "loan";
    }

    @GetMapping("/{id}/del")
    public String delete(@PathVariable Long id) {
        loanService.deleteById(id);

        return "redirect:/loan";
    }
}
