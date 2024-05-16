package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Adoption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Profile({JPA})
public class AdoptionJpaDao extends AbstractBaseJpaDao<Adoption>{
    public AdoptionJpaDao(EntityManager em, EntityTransaction tx) {
        super(em, tx);
    }

    @Override
    public List<Adoption> findAll() {
        return null;
    }

    @Override
    public Adoption findById(int id) {
        return null;
    }

    @Override
    public Adoption insert(Adoption adoption) {
        return null;
    }

    @Override
    public boolean update(Adoption adoption) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }
}
