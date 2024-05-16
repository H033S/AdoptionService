package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Adopter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Transactional
@Primary
public class AnilAdopterJpaDao implements BaseDao<Adopter> {


    private final EntityManager em;

    public AnilAdopterJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Adopter> findAll() {
        TypedQuery<Adopter> adopters = em.createQuery("SELECT ADT FROM Adopter ADT", Adopter.class);
        return adopters.getResultList();
    }

    @Override
    public Adopter findById(int id) {
        return em.find(Adopter.class, id);
    }

    @Override
    public Adopter insert(Adopter adopter) {
        em.persist(adopter);
        return adopter;
    }

    @Override
    public boolean update(Adopter adopter) {
        em.merge(adopter);
        return true;
    }

    @Override
    public boolean delete(int i) {
        var entity = findById(i);
        em.remove(entity);
        return true;
    }
}
