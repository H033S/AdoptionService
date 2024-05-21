package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Pet;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

@Repository
@Profile({JPA})
public interface PetJpaDao extends JpaRepository<Pet, Integer> {}

