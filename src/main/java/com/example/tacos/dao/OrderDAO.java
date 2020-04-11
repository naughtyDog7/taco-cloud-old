package com.example.tacos.dao;

import com.example.tacos.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends CrudRepository<Order, Long> {
//    List<Order> findAll();
//    Order findOne(int id);
//    void save(Order order);
//    void update(Order order);
//    void delete(Order order);
}