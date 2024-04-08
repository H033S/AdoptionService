package com.nazmen.tech.adoption.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.nazmen.tech.adoption.domain.Entity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class Adoption extends Entity{

    @NotNull(message = "{validation.adoption.adopter.null}")
    private Adopter adopter;

    @NotNull(message = "{validation.adoption.pet.null}")
    private Pet pet;

    @Future(message = "{validation.adoption.adoptionDate.past}")
    private LocalDate adoptionDate;

    public Adoption(
        UUID id, 
        Adopter adopter, 
        Pet pet,
        LocalDate adoptionDate) {
            
            super(id);
            this.adopter = adopter;
            this.pet = pet;
            this.adoptionDate = adoptionDate;
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }
    
}
