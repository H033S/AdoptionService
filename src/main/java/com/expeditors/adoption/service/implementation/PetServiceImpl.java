package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl
        extends AbstractBaseService<Pet>
        implements PetService {

    public PetServiceImpl(BaseDao<Pet> petDAO) {
        super(petDAO);
    }

}
