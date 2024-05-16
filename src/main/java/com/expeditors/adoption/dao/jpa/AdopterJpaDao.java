package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Adopter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Profile({JPA})
public class AdopterJpaDao extends AbstractBaseJpaDao<Adopter>  {

    public AdopterJpaDao(EntityManager em, EntityTransaction tx) {
        super(em, tx);
    }

    @Override
    public List<Adopter> findAll() {
        TypedQuery<Adopter> adopters = em.createQuery("SELECT ADT FROM Adopter ADT", Adopter.class);
        return adopters.getResultList();
    }

    @Override
    public Adopter findById(int id) {
        return null;
    }

    @Override
    public Adopter insert(Adopter adopter) {
        return null;
    }

    @Override
    public boolean update(Adopter adopter) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }
}
