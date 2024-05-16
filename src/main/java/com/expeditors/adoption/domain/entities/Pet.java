package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pet extends AbstractEntity {

    @NotNull(message = "{validation.petBreed.null}")
    @Column(
            name = "pet_breed",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private PetBreed petBreed;    

    @NotNull(message = "{validation.petType.null}")
    @Column(
            name = "pet_type",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private PetType petType;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    @Column(
            name = "pet_name",
            nullable = false
    )
    private String petName;


    public Pet() {
    }

    public Pet(
            int id,
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

    @Override
    public String toString() {
        return "Pet{" + " id=" + id +
                ", petBreed=" + petBreed +
                ", petType=" + petType +
                ", petName='" + petName + '\'' +
                '}';
    }
}
