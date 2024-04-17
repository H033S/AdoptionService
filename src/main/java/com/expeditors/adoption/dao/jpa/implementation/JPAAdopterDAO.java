package com.expeditors.adoption.dao.jpa.implementation;

import com.expeditors.adoption.dao.jpa.JPAAbstractCrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;


@Repository
@Profile(value=JPA)
public class JPAAdopterDAO extends JPAAbstractCrudDAO<Adopter> {
}
