package com.nazmen.tech.adoption.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PetTest {
    
    @Test
    public void createPet_ValidationReturnTrue_WithValidObject(){
        Pet pet = new Pet(
            UUID.randomUUID(), 
            PetBreed.SIAMESE, 
            PetType.CAT, 
            "Suki");

        assertTrue(pet.isModelValid());
    }

    @Test
    public void createPet_ValidationReturnFalse_WithIncorrectObject(){
        Pet pet = new Pet(
            UUID.randomUUID(), 
            null, 
            null, 
            "");

        assertFalse(pet.isModelValid());
        assertEquals(3, pet.getModelViolations().size());
    }
}
