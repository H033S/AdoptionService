package com.expeditors.adoption.dto;

import java.time.LocalDate;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;

import lombok.Builder;

@Builder
public class AdoptionResponse {
    
    private int id;
    private Adopter adopter;
    private Pet pet;
    private LocalDate adoptionDate;

    public static AdoptionResponse fromAdoption(Adoption adoption){
        
        return AdoptionResponse.builder()
        .adopter(adoption.getAdopter())
        .pet(adoption.getPet())
        .adoptionDate(adoption.getAdoptionDate())
        .build();
    } 
}
