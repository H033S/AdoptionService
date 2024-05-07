package com.expeditors.adoption.unittests.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.domain.entities.PetBreed;
import com.expeditors.adoption.domain.entities.PetType;
import org.junit.jupiter.api.Test;


public class PetTest {
    
    @Test
    public void createPet_ValidationReturnTrue_WithValidObject(){
        Pet pet = new Pet(
            1,
            PetBreed.SIAMESE,
            PetType.CAT,
            "Suki");

        assertTrue(pet.isModelValid());
    }

    @Test
    public void createPet_ValidationReturnFalse_WithIncorrectObject(){
        Pet pet = new Pet(
            1,
            null, 
            null, 
            "");

        assertFalse(pet.isModelValid());
        assertEquals(3, pet.getModelViolations().size());
    }
}
