package com.example.tacos.controller;


import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Ingredient.Type;
import com.example.tacos.model.Order;
import com.example.tacos.model.Taco;
import com.example.tacos.service.IngredientService;
import com.example.tacos.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private TacoService tacoService;
    private IngredientService ingredientService;


    @Autowired
    public DesignTacoController(IngredientService ingredientService, TacoService tacoService) {
        this.ingredientService = ingredientService;
        this.tacoService = tacoService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>(ingredientService.findAll());
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute Taco design,
                                Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach((i) -> log.error(i.getDefaultMessage()));
            return "design";
        }
        log.info("Before saving taco");
        updateIngredientsList(design);
        order.addTaco(design);
        tacoService.save(design);
        log.info("After saving taco");
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private void updateIngredientsList(Taco design) {
        design.setIngredientsList(design.getIngredients()
                .stream()
                .map(i -> ingredientService.findOne(i))
                .collect(Collectors.toList())
        );
    }
}
