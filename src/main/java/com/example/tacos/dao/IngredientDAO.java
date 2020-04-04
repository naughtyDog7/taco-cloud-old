package com.example.tacos.dao;

import com.example.tacos.model.Ingredient;

import java.util.List;

public interface IngredientDAO {
    List<Ingredient> findAll();
    Ingredient findOne(String id);
    void save(Ingredient ingredient);
}
