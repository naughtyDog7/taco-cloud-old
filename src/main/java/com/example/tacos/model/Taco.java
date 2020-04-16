package com.example.tacos.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotNull(message = "Please choose items (min 1)")
    @Size(min = 1, message = "At least one item should be chosen")

    @Transient
    private List<String> ingredients;

//    @ManyToMany(mappedBy = "tacos")
    @ToString.Exclude
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredientsList;

//    @ToString.Exclude
//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable (
//            name = "taco_orders",
//            joinColumns = {@JoinColumn(name = "taco_id")},
//            inverseJoinColumns = {@JoinColumn(name = "order_id")}
//    )
//    private List<Order> orders = new ArrayList<>();
//
//    public void addOrder(Order order) {
//        orders.add(order);
//    }

    private Date createdAt;

    @PrePersist
    void createdAt() {
        createdAt = new Date();
    }

    public double totalPrice() {
        return Double.parseDouble(
                String.format( "%.2f",
                ingredientsList
                .stream()
                .mapToDouble(Ingredient::getPrice)
                .sum()));
    }
}
