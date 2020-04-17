package com.example.tacos.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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
    @Size(min = 3, message = "Street should be at least 3 characters")
    private String street;

    @Pattern(regexp = "(\\+998)?[\\s-]?\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}", message = "Phone number is incorrect")
    private String phoneNum;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    public double totalPrice() {
        return Double.parseDouble(
                String.format("%.2f",
                        tacos
                                .stream()
                                .mapToDouble(Taco::totalPrice)
                                .sum()));
    }

    @ManyToOne
    private User user;

    private LocalDateTime placedAt;

    private String cardNum;

    private boolean ordered;

    @PreUpdate
    @PrePersist
    void placedAt() {
        this.placedAt = LocalDateTime.now();
    }
}
