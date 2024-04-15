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
    
//    private String adopterName;
//    private String adopterPhoneNumber;
//    private String petBreed;
//    private String petType;
//    private String petName;
//    private LocalDate adoptionDate;
//
//    public static AdoptionResponse fromAdoption(Adoption adoption){
//
//        return AdoptionResponse.builder()
//                .adopterName(adoption.getAdopter().getAdopterName())
//                .adopterPhoneNumber(adoption.getAdopter().getPhoneNumber())
//                .petBreed(adoption.getPet().getPetBreed().toString())
//                .petType(adoption.getPet().getPetType().toString())
//                .petName(adoption.getPet().getPetName())
//                .adoptionDate(adoption.getAdoptionDate())
//                .build();
//    }


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
