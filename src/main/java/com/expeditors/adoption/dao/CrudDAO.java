package com.expeditors.adoption.dao;


import com.expeditors.adoption.domain.Entity;

import java.util.List;


public interface CrudDAO<TEntity extends Entity> {
    TEntity insert(TEntity tEntity);

    boolean update(TEntity tEntity);

    boolean delete(int i);

    TEntity findById(int i);

    List<TEntity> findAll();
}
