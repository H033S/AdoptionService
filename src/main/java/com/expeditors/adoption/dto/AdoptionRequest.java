package com.expeditors.adoption.dto;

import java.time.LocalDate;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionRequest {
    
    @NotNull
    private Adopter adopter;

    @NotNull
    private Pet pet;

    @NonNull
    @Future
    private LocalDate adoptionDate;


    public Adoption toAdoption(){
        return Adoption
        .builder()
        .adopter(adopter)
        .pet(pet)
        .adoptionDate(adoptionDate)
        .build();
    }
}
