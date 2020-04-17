package com.example.tacos.controller;

import com.example.tacos.model.RegistrationForm;
import com.example.tacos.model.User;
import com.example.tacos.service.UserService;
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
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
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
        //checks if username exists, if yes, add error and return
        if (userService.usernameTaken(form.getUsername())) {
            errors.rejectValue("username", "errorCode", "Username is not available");
        }
        if (errors.hasErrors()) {
            return "registration";
        }
        User user = form.toUser(passwordEncoder);
        userService.save(user);
        return "redirect:/login";
    }

}
