package com.example.tacos.controller;

import com.example.tacos.model.CreditCardNum;
import com.example.tacos.model.Order;
import com.example.tacos.model.User;
import com.example.tacos.property.OrderProps;
import com.example.tacos.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProps orderProps;

    @Autowired
    public OrderController(OrderService orderService, OrderProps orderProps) {
        this.orderService = orderService;
        this.orderProps = orderProps;
    }

    private void addOrders(Model model, User user) {
        List<Order> allOrders = orderService.findOrdersByUserId(user, PageRequest.of(0, orderProps.getOrdersListLength()));
        orderService.findCurrent(user)
                .ifPresent(i -> model.addAttribute("currentOrder", i));
        model.addAttribute("orders", allOrders);
    }

    @ModelAttribute
    public CreditCardNum creditCardNum() {
        return new CreditCardNum();
    }

    @GetMapping
    public String orders(Model model, @AuthenticationPrincipal User user) {
        addOrders(model, user);
        model.addAttribute("creditCardNumber", creditCardNum());
        return "orders";
    }


    @PostMapping()
    public String processOrder(@Valid CreditCardNum cardNumber, Errors errors,
                               Model model, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            addOrders(model, user);
            model.addAttribute("cardNumber", cardNumber);
            return "orders";
        }
        Order order = orderService.findCurrent(user)
                .orElseThrow(() -> new IllegalArgumentException(
                        "There must be current order on this stage"));
        order.setOrdered(true);
        order.setCardNum(cardNumber.getCardNum());
        orderService.save(order);
        log.info("Order completed: " + order);
        return "redirect:/orders";
    }
}