package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import static java.lang.StringTemplate.STR;

public class Adoption extends Entity{

    @NotNull(message = "{validation.adoption.adopter.null}")
    private Adopter adopter;

    @NotNull(message = "{validation.adoption.pet.null}")
    private Pet pet;

    @Future(message = "{validation.adoption.adoptionDate.past}")
    private LocalDate adoptionDate;

    public Adoption(
        int id,
        Adopter adopter, 
        Pet pet,
        LocalDate adoptionDate) {
            
            super(id);
            this.adopter = adopter;
            this.pet = pet;
            this.adoptionDate = adoptionDate;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
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


    @Override
    public String toString() {
        return STR."\{getAdopter().getAdopterName()} adopted a \{getPet().getPetType()} called \{getPet().getPetName()} @\{getAdoptionDate()}";
    }
}
