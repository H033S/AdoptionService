package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Adoption extends Entity{

    @NotNull(message = "{validation.adoption.adopter.null}")
    private Adopter adopter;

    @NotNull(message = "{validation.adoption.pet.null}")
    private Pet pet;

    @Future(message = "{validation.adoption.adoptionDate.past}")
    private LocalDate adoptionDate;


    public Adoption(
        int id,
        Adopter Adopter,
        Pet pet,
        LocalDate adoptionDate) {
        
        super(id);
        this.adopter = adopter;
        this.pet = pet;
        this.adoptionDate = adoptionDate;
    }

    

    public Adoption(
        Adopter adopter,
        Pet pet,
        LocalDate adoptionDate) {

            this(0, adopter, pet, adoptionDate);
    }

    
    @Override
    public String toString() {
        return getAdopter().getAdopterName() + 
        "adopted a" + getPet().getPetType() + 
        "called" + getPet().getPetName() + getAdoptionDate();
    }
}
