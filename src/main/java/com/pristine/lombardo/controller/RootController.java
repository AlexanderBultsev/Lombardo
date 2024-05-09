package com.pristine.lombardo.controller;

import com.pristine.lombardo.dto.UserDTO;
import com.pristine.lombardo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
        } catch(Exception e) {
            log.error(e.getMessage());
            return "redirect:/registration?tel_invalid";
        }
        return "redirect:/registration?success";
    }
}
