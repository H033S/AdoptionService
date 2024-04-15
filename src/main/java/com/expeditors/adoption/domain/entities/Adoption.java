package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
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

    @Override
    public String toString() {
        return getAdopter().getAdopterName() + 
        " adopted a " + getPet().getPetType() +
        " called " + getPet().getPetName() + getAdoptionDate();
    }

}
