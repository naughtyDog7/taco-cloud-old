package com.example.tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Order {
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(min = 3, message = "City should be at least 3 characters")
    private String city;

    @NotBlank(message = "Street is required")
    @Size(min = 3, message = "Street should be at least 3 characters")
    private String street;

    @Pattern(regexp = "(\\+998)?[\\s-]?\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}", message = "Phone number is incorrect")
    private String phoneNum;

    @CreditCardNumber(message = "Incorrect credit card number")
    private String cardNum;
}
