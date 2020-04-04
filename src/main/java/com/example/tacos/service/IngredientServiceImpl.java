package com.example.tacos.service;

import com.example.tacos.dao.IngredientDAOImpl;
import com.example.tacos.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientDAOImpl ingredientDAO;

    @Autowired
    public IngredientServiceImpl(IngredientDAOImpl ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientDAO.findAll();
    }

    @Override
    public Ingredient findOne(String id) {
        return ingredientDAO.findOne(id);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientDAO.save(ingredient);
    }
}
