package com.expeditors.adoption.dao.inmemory.implementation;

import com.expeditors.adoption.dao.inmemory.InMemoryAbstractBaseDao;
import com.expeditors.adoption.domain.entities.Adoption;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.IN_MEMORY;


@Repository
@Profile(value = IN_MEMORY)
public class InMemoryAdoptionDao extends InMemoryAbstractBaseDao<Adoption> {
}
