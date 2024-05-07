package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
        return "Adoption{" +
                "adopter=" + adopter +
                ", pet=" + pet +
                ", adoptionDate=" + adoptionDate +
                '}';
    }
}
