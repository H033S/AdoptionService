package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final CrudDAO<Pet> petDAO;

    public PetServiceImpl(CrudDAO<Pet> petDAO) {
        this.petDAO = petDAO;
    }

    @Override
    public Pet getPetById(int petId) {
        return petDAO.findById(petId);
    }
}
