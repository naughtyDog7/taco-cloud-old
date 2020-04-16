package com.example.tacos.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotBlank
    @Size(min = 8, message = "Username min length 8")
    private String username;

    @NotBlank
    @Size(min = 8, message = "Password min length 8")
    private String password;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String fullName;

    @NotBlank(message = "City is required")
    @Size(min = 3, message = "City should be at least 3 characters")
    private String city;

    @NotBlank(message = "Street is required")
    @Size(min = 3, message = "Street should be at least 3 characters")
    private String street;

    @Pattern(regexp = "(\\+998)?[\\s-]?\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}", message = "Phone number is incorrect")
    private String phoneNum;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,
                passwordEncoder.encode(password),
                fullName, city, street, phoneNum);
    }
}
