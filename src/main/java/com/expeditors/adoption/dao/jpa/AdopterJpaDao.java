package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Adopter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA_TEST;

@Repository
@Profile({JPA, JPA_TEST})
public class AdopterJpaDao implements BaseDao<Adopter> {

    @PersistenceContext
    private EntityManager em;

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
    public boolean delete(int id) {
        Adopter adopter = findById(id);

        if(adopter == null){
            return false;
        }
        em.remove(adopter);
        return true;
    }
}
