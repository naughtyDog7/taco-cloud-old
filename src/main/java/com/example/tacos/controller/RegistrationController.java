package com.example.tacos.controller;

import com.example.tacos.dao.UserDAO;
import com.example.tacos.model.RegistrationForm;
import com.example.tacos.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute(registrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "registration";
        }
        User user = form.toUser(passwordEncoder);
        log.info("user " + user);
        userDAO.save(user);
        return "redirect:/login";
    }

}
