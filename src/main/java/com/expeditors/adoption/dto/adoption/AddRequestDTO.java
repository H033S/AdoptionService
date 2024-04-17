package com.expeditors.adoption.dto.adoption;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AddRequestDTO {

    private AddRequestDTO(){}

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AddAdoptionRequest{

        @Future
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate adoptionDate;

        @NotNull
        private int adopterId;

        @NotNull
        private int petId;
    }

    public static Adoption createAdoption(
            LocalDate adoptionDate,
            Adopter associatedAdopter,
            Pet associatedPet) {

        return new Adoption(0, associatedAdopter, associatedPet, adoptionDate);
    }
}
