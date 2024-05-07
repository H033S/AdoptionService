package com.expeditors.adoption.service;

import com.expeditors.adoption.domain.Entity;

import java.util.List;

public interface BaseService<TEntity extends Entity> {
    List<TEntity> getAllEntities();

    TEntity addEntity(TEntity tEntity);

    boolean updateEntity(TEntity tEntity);

    boolean deleteEntity(int id);

    TEntity getEntityById(int id);
}
