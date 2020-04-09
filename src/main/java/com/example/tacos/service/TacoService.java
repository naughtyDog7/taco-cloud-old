package com.example.tacos.service;

import com.example.tacos.model.Taco;

import java.util.List;

public interface TacoService {
    List<Taco> findAll();
    Taco findOne(long id);
    void save(Taco taco);
    void delete(Taco taco);
}
