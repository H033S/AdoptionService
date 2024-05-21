package com.expeditors.adoption.dao.jpa.adapters;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.dao.jpa.AdoptionJpaDao;
import com.expeditors.adoption.domain.entities.Adoption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Profile({JPA})
public class AdoptionJpaDaoAdapter implements BaseDao<Adoption> {

    private final AdoptionJpaDao repo;

    public AdoptionJpaDaoAdapter(AdoptionJpaDao repo) {
        this.repo = repo;
    }

    @Override
    public List<Adoption> findAll() {
        return repo.findAllAdoptionWithAdopterAndPet();
    }

    @Override
    public Adoption findById(int id) {
        return repo
                .findById(id)
                .orElse(null);
    }

    @Override
    public Adoption insert(Adoption adoption) {
        return repo.save(adoption);
    }

    @Override
    public boolean update(Adoption adoption) {

        if(!repo.existsById(adoption.getId())){
            return false;
        }
        repo.save(adoption);
        return true;
    }

    @Override
    public boolean delete(int id) {

        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
