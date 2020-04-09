package com.example.tacos.dao;

import com.example.tacos.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoDAO extends CrudRepository<Taco, Long> {
//    List<Taco> findAll();
//    Taco findOne(int id);
//    void save(Taco taco);
//    void update(Taco taco);
//    void delete(Taco taco);
}
