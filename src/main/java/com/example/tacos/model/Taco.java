package com.example.tacos.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotNull(message = "Please choose items (min 1)")
    @Size(min = 1, message = "At least one item should be chosen")
    private List<String> ingredients;
}
