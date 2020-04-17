package com.example.tacos.controller;

import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Ingredient.Type;
import com.example.tacos.model.Order;
import com.example.tacos.model.Taco;
import com.example.tacos.model.User;
import com.example.tacos.service.IngredientService;
import com.example.tacos.service.OrderService;
import com.example.tacos.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final TacoService tacoService;
    private final IngredientService ingredientService;
    private final OrderService orderService;

    @Autowired
    public DesignTacoController(IngredientService ingredientService, TacoService tacoService, OrderService orderService) {
        this.ingredientService = ingredientService;
        this.tacoService = tacoService;
        this.orderService = orderService;
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
                                Errors errors, @ModelAttribute Order order,
                                @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach((i) -> log.error(i.getDefaultMessage()));
            return "design";
        }
        updateIngredientsList(design);
        order = orderService.findCurrent(user)
                .orElse(order);
        order.addTaco(design);
        setOrderInfo(order, user);
        tacoService.save(design);
        orderService.save(order);

        log.info("Processing design: " + design);
        return "redirect:/orders";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    //saving info which we get from registration form to order
    private void setOrderInfo(Order order, User user) {
        order.setName(user.getFullName());
        order.setCity(user.getCity());
        order.setStreet(user.getStreet());
        order.setPhoneNum(user.getPhoneNum());
        order.setUser(user);
    }

    //copying ingredients from temp arr into main list
    private void updateIngredientsList(Taco design) {
        design.setIngredientsList(design.getIngredients()
                .stream()
                .map(ingredientService::findOne)
                .collect(Collectors.toList())
        );
    }
}
