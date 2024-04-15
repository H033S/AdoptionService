package com.expeditors.adoption.dto;

import java.time.LocalDate;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionResponse {

    private Adopter adopter;
    private Pet pet;
    private LocalDate adoptionDate;

    public static AdoptionResponse fromAdoption(Adoption adoption){

        return AdoptionResponse.builder()
                .adoptionDate(adoption.getAdoptionDate())
                .adopter(adoption.getAdopter())
                .pet(adoption.getPet())
                .build();
    }
}
