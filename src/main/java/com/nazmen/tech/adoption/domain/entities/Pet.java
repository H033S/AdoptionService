package com.nazmen.tech.adoption.domain.entities;

import java.util.UUID;

import com.nazmen.tech.adoption.domain.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pet extends Entity{

    @NotNull(message = "{validation.petBreed.null}")
    private PetBreed petBreed;    

    @NotNull(message = "{validation.petType.null}")
    private PetType petType;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String petName;


    public Pet(
        UUID id,
        PetBreed petBreed, 
        PetType petType, 
        String petName) {
            
            super(id);
            this.petBreed = petBreed;
            this.petType = petType;
            this.petName = petName;
    }


    public PetBreed getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(PetBreed petBreed) {
        this.petBreed = petBreed;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

}
