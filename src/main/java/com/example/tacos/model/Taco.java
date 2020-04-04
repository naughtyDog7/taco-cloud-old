package com.example.tacos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotNull(message = "Please choose items (min 1)")
    @Size(min = 1, message = "At least one item should be chosen")

    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    private LocalDateTime createdAt;

    @PrePersist
    void createdAt() {
        createdAt = LocalDateTime.now();
    }
}
