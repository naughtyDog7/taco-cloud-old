package com.example.tacos.service;

import com.example.tacos.dao.IngredientDAO;
import com.example.tacos.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientDAO ingredientDAO;

    @Autowired
    public IngredientServiceImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientDAO.findAll().forEach(ingredientList::add);
        return ingredientList;
    }

    @Override
    public Ingredient findOne(String id) {
        return ingredientDAO.findById(id).orElse(null);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientDAO.save(ingredient);
    }
}
