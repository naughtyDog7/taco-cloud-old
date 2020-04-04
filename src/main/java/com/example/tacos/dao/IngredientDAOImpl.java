package com.example.tacos.dao;

import com.example.tacos.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDAOImpl implements IngredientDAO {

    private SessionFactory sessionFactory;
    @Autowired
    public IngredientDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Ingredient> findAll() {
        return (List<Ingredient>) sessionFactory.openSession().createQuery("FROM ingredients").list();
    }

    @Override
    public Ingredient findOne(String id) {
        return sessionFactory.openSession().get(Ingredient.class, id);
    }

    @Override
    public void save(Ingredient ingredient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(ingredient);
        transaction.commit();
        session.close();
    }
}
