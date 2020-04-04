package com.example.tacos.service;

import com.example.tacos.model.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient findOne(String id);
    void save(Ingredient ingredient);
}
