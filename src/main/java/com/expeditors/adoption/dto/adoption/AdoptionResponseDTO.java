package com.expeditors.adoption.dto.adoption;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.PetBreed;
import com.expeditors.adoption.domain.entities.PetType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AdoptionResponseDTO {
    private AdoptionResponseDTO() {
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdoptionResponse {
        private int id;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate adoptionDate;
        private AdopterResponse adopterResponse;
        private PetResponse petResponse;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdopterResponse{
        private int id;
        private String name;
        private String phoneNumber;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PetResponse{
        private int id;
        private String name;
        private PetBreed breed;
        private PetType type;
    }

    public static AdoptionResponse createFromAdoption(Adoption adoption){

        return AdoptionResponse
                .builder()
                .id(adoption.getId())
                .adopterResponse(AdopterResponse
                        .builder()
                        .id(adoption.getAdopter().getId())
                        .name(adoption.getAdopter().getAdopterName())
                        .phoneNumber(adoption.getAdopter().getPhoneNumber())
                        .build())
                .petResponse(PetResponse
                        .builder()
                        .id(adoption.getAdopter().getId())
                        .name(adoption.getPet().getPetName())
                        .breed(adoption.getPet().getPetBreed())
                        .type(adoption.getPet().getPetType())
                        .build())
                .adoptionDate(adoption.getAdoptionDate())
                .build();
    }
}
