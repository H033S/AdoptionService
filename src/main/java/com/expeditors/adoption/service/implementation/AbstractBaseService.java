package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.Entity;
import com.expeditors.adoption.service.BaseService;

import java.util.List;
import java.util.Objects;

public abstract class AbstractBaseService<TEntity extends Entity>
        implements BaseService<TEntity> {

    protected final BaseDAO<TEntity> entityDAO;

    protected AbstractBaseService(BaseDAO<TEntity> entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Override
    public List<TEntity> getAllEntities(){
        return entityDAO.findAll();
    }

    @Override
    public TEntity addEntity(TEntity tEntity){
        if(Objects.isNull(tEntity)){
            throw new IllegalArgumentException(
                    "Adoption cannot be null");
        }

        return entityDAO.insert(tEntity);
    }

    @Override
    public boolean updateEntity(TEntity tEntity){
        if(Objects.isNull(tEntity)){
            throw new IllegalArgumentException(
                    "Adoption cannot be null");
        }

        return entityDAO.update(tEntity);
    }

    @Override
    public boolean deleteEntity(int id){
        return entityDAO.delete(id);
    }

    @Override
    public TEntity getEntityById(int id){
        return entityDAO.findById(id);
    }

}
