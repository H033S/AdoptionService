package com.expeditors.adoption.dao;


import com.expeditors.adoption.domain.Entity;

import java.util.List;


public interface BaseDAO<TEntity extends Entity> {
    List<TEntity> findAll();
    TEntity findById(int i);
    TEntity insert(TEntity tEntity);

    boolean update(TEntity tEntity);

    boolean delete(int i);

}
