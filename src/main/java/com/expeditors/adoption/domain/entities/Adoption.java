package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Adoption extends AbstractEntity {

    @OneToOne
    @NotNull(message = "{validation.adoption.adopter.null}")
    private Adopter adopter;

    @OneToOne
    @NotNull(message = "{validation.adoption.pet.null}")
    private Pet pet;

    @Future(message = "{validation.adoption.adoptionDate.past}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;

    public Adoption(int id, Adopter adopter, Pet pet, LocalDate adoptionDate) {
        this.id = id;
        this.adopter = adopter;
        this.pet = pet;
        this.adoptionDate = adoptionDate;
    }
}
