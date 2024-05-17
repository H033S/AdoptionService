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
public class PetJpaDao extends AbstractBaseJpaAdapterDao<Pet> {


    public PetJpaDao(AbstractJPARepo<Pet> repo) {
        super(repo);
    }
}
