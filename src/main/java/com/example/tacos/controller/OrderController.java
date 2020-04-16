package com.example.tacos.controller;

import com.example.tacos.model.CreditCardNum;
import com.example.tacos.model.Order;
import com.example.tacos.model.User;
import com.example.tacos.service.OrderService;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private void addOrders(Model model, long userId) {
        List<Order> allOrders = orderService.findOrdersByUserId(userId);
        if(allOrders != null && !allOrders.isEmpty()) {
            model.addAttribute("currentOrder", allOrders.get(allOrders.size() - 1));
            Collections.reverse(allOrders);
        }
        model.addAttribute("orders", allOrders);
    }

    @ModelAttribute
    public CreditCardNum creditCardNum() {
        return new CreditCardNum();
    }

    @GetMapping
    public String orders(Model model, @AuthenticationPrincipal User user) {
        addOrders(model, user.getId());
        model.addAttribute("creditCardNumber", creditCardNum());
        return "orders";
    }


    @PostMapping()
    public String processOrder(@Valid CreditCardNum cardNumber, Errors errors,
                               Model model, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            addOrders(model, user.getId());
            model.addAttribute("cardNumber", cardNumber);
            log.info(errors.getFieldErrors().toString());
            return "orders";
        }
        List<Order> allOrders = orderService.findAll();
        Order order = allOrders.get(allOrders.size() - 1);
        order.setOrdered(true);
        order.setCardNum(cardNumber.getCardNum());
        orderService.save(order);
        log.info("Order completed: " + order);
        return "redirect:/";
    }

//
//    @GetMapping("/current")
//    public String orderForm() {
//        return "orderForm";
//    }

//    @PostMapping
//    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }
//        order.setUser(user);
//        orderService.save(order);
//        log.info("Order submitted " + order.toString());
//        sessionStatus.setComplete();
//        return "redirect:/orders";
//    }
}
