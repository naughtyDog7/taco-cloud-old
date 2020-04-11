package com.example.tacos.controller;

import com.example.tacos.dao.UserDAO;
import com.example.tacos.model.RegistrationForm;
import com.example.tacos.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {

        User user = form.toUser(passwordEncoder);
        log.info("user " + user);
        userDAO.save(user);
        return "redirect:/login";
    }

}
