package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionJpaDao extends JpaRepository<Adoption, Integer> {}

