package com.example.tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(min = 3, message = "City should be at least 3 characters")
    private String city;

    @NotBlank(message = "Street is required")
    @Size(min = 3, message = "Street should be at le    ast 3 characters")
    private String street;

    @Pattern(regexp = "(\\+998)?[\\s-]?\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}", message = "Phone number is incorrect")
    private String phoneNum;

    @CreditCardNumber(message = "Incorrect credit card number")
    private String cardNum;
//
//    @ManyToMany(mappedBy = "orders")
//    private List<Taco> tacos = new ArrayList<>();

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @ManyToOne
    private User user;

    private LocalDateTime placedAt;

    @PrePersist
    void placedAt() {
        this.placedAt = LocalDateTime.now();
    }
//
//    public void addDesign(Taco design) {
//        this.tacos.add(design);
//        design.addOrder(this);
//    }
}
