package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Profile({JPA})
public class PetJpaDao extends AbstractBaseJpaDao<Pet>{
    public PetJpaDao(EntityManager em, EntityTransaction tx) {
        super(em, tx);
    }

    @Override
    public List<Pet> findAll() {
        return null;
    }

    @Override
    public Pet findById(int id) {
        return null;
    }

    @Override
    public Pet insert(Pet pet) {
        return null;
    }

    @Override
    public boolean update(Pet pet) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }
}
