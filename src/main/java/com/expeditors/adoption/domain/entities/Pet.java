package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
public class Pet extends Entity{

    @NotNull(message = "{validation.petBreed.null}")
    private PetBreed petBreed;    

    @NotNull(message = "{validation.petType.null}")
    private PetType petType;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String petName;

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

}
