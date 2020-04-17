package com.example.tacos.service;

import com.example.tacos.dao.OrderDAO;
import com.example.tacos.model.Order;
import com.example.tacos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> findOrdersByUserId(User user, Pageable pageable) {
        return orderDAO.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        orderDAO.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findOne(long id) {
        return orderDAO.findById(id).orElse(null);
    }

    @Override
    public void save(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Override
    public Optional<Order> findCurrent(User user) {
        return Optional.ofNullable(
                orderDAO.findTopByUserAndOrderedFalseOrderByPlacedAtDesc(user));
    }
}
