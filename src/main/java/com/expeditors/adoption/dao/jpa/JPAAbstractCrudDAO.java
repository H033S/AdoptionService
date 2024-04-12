package com.expeditors.adoption.dao.jpa;


import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class JPAAbstractCrudDAO<TEntity extends Entity> implements CrudDAO<TEntity> {
    private final Map<Integer,TEntity> entities;
    private static int nextId;


    static {
        nextId = 0;
    }

    public JPAAbstractCrudDAO() {
        entities = new HashMap<>();
    }

    public TEntity insert(TEntity entity) {
        Objects.requireNonNull(entity);

        nextId++;
        entity.setId(nextId);
        entities.put(entity.getId(), entity);
        return entity;
    }

    public boolean update(TEntity entity) {
        return entities.putIfAbsent(entity.getId(), entity) != null;
    }

    public boolean delete(int id) {
        return entities.remove(id) != null;
    }
    public TEntity findById(int id) {
        return entities.get(id);
    }

    public List<TEntity> findAll() {
        return entities.values().stream().toList();
    }

    @Override
    public void clear() {
       nextId = 0;
       entities.clear();
    }

    

}
