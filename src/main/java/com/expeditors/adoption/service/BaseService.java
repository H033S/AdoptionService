package com.expeditors.adoption.service;

import com.expeditors.adoption.domain.AbstractEntity;

import java.util.List;

public interface BaseService<TEntity extends AbstractEntity> {
    List<TEntity> getAllEntities();

    TEntity addEntity(TEntity tEntity);

    boolean updateEntity(TEntity tEntity);

    boolean deleteEntity(int id);

    TEntity getEntityById(int id);
}
