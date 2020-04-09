package com.example.tacos.dao;

import com.example.tacos.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDAO extends CrudRepository<Ingredient, String> {
//    List<Ingredient> findAll();
//    Ingredient findOne(String id);
//    void save(Ingredient ingredient);
}
