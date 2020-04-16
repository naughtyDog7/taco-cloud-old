package com.example.tacos.service;

import com.example.tacos.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findOne(long id);
    void save(Order order);
    void delete(Order order);
    List<Order> findOrdersByUserId(long id);
}
