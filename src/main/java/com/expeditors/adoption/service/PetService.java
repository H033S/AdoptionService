package com.expeditors.adoption.service;

import com.expeditors.adoption.domain.entities.Pet;

public interface PetService {
    Pet getPetById(int petId);
}
