package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class AbstractBaseJpaDao<TEntity extends AbstractEntity>
        implements BaseDao<TEntity> {

    protected final EntityManager em;
    protected final EntityTransaction tx;

    public AbstractBaseJpaDao(EntityManager em, EntityTransaction tx) {
        this.em = em;
        this.tx = tx;
    }
}
