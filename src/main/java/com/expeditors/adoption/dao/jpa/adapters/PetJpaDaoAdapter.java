package com.expeditors.adoption.dao.jpa.adapters;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.dao.jpa.PetJpaDao;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA_TEST;

@Repository
@Profile({JPA, JPA_TEST})
public class PetJpaDaoAdapter implements BaseDao<Pet> {

    private final PetJpaDao repo;

    public PetJpaDaoAdapter(PetJpaDao repo) {
        this.repo = repo;
    }

    @Override
    public List<Pet> findAll() {
        return repo.findAll();
    }

    @Override
    public Pet findById(int id) {
        return repo
                .findById(id)
                .orElse(null);
    }

    @Override
    public Pet insert(Pet pet) {
        return repo.save(pet);
    }

    @Override
    public boolean update(Pet pet) {
        repo.save(pet);
        return true;
    }

    @Override
    public boolean delete(int id) {
        repo.deleteById(id);
        return true;
    }
}
