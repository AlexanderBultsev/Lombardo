package com.pristine.lombardo.controller;

import com.pristine.lombardo.dto.ClientDTO;
import com.pristine.lombardo.dto.PropertyDTO;
import com.pristine.lombardo.entity.Client;
import com.pristine.lombardo.service.ClientService;
import com.pristine.lombardo.service.LoanService;
import com.pristine.lombardo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    LoanService loanService;

    @Autowired
    PropertyService propertyService;

    @PostMapping("/add")
    public String add(ClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);

        return "redirect:/client/" + client.getId();
    }


    @GetMapping("")
    public String showClientList(Model model, @RequestParam(required = false) String findTel) {
        model.addAttribute("clientDTO", new ClientDTO());
        if (findTel != null && !findTel.isEmpty()) {
            model.addAttribute("clients", clientService.findByTel(findTel));
        } else {
            model.addAttribute("clients", clientService.findAll());
        }
        return "client-list";
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        model.addAttribute("propertyDTO", new PropertyDTO());

        return "client";
    }

    @GetMapping("/{id}/del")
    public String delete(@PathVariable Long id) {
        clientService.deleteById(id);

        return "redirect:/client";
    }
}
