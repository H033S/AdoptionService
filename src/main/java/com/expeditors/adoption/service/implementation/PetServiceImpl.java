package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl
        extends AbstractBaseService<Pet>
        implements PetService {

    public PetServiceImpl(BaseDAO<Pet> petDAO) {
        super(petDAO);
    }

}
