package com.expeditors.adoption.dao;


import com.expeditors.adoption.domain.AbstractEntity;

import java.util.List;


public interface BaseDao<TEntity extends AbstractEntity> {
    List<TEntity> findAll();
    TEntity findById(int id);
    TEntity insert(TEntity tEntity);
    boolean update(TEntity tEntity);
    boolean delete(int i);
}
