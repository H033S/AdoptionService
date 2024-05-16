package com.expeditors.adoption.dao.repository;

import com.expeditors.adoption.domain.entities.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public interface AdopterRepo extends JpaRepository<Adopter, Integer> {
}
