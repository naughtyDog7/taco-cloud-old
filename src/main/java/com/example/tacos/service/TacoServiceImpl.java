package com.example.tacos.service;

import com.example.tacos.dao.TacoDAO;
import com.example.tacos.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TacoServiceImpl implements TacoService {
    private final TacoDAO tacoDAO;

    @Autowired
    public TacoServiceImpl(TacoDAO tacoDAO) {
        this.tacoDAO = tacoDAO;
    }

    @Override
    public List<Taco> findAll() {
        List<Taco> tacos = new ArrayList<>();
        tacoDAO.findAll().forEach(tacos::add);
        return tacos;
    }

    @Override
    public Taco findOne(long id) {
        return tacoDAO.findById(id).orElse(null);
    }

    @Override
    public void save(Taco taco) {
        tacoDAO.save(taco);
    }

    @Override
    public void delete(Taco taco) {
        tacoDAO.delete(taco);
    }
}
