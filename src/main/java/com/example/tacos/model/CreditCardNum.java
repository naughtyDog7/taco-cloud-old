package com.example.tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class CreditCardNum {
    @CreditCardNumber(message = "Invalid credit card number")
    private String cardNum;
}
