package com.example.tacos.service;

import com.example.tacos.model.Order;
import com.example.tacos.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Order findOne(long id);
    void save(Order order);
    void delete(Order order);
    List<Order> findOrdersByUserId(User user, Pageable pageable);
    Optional<Order> findCurrent(User user);
}
