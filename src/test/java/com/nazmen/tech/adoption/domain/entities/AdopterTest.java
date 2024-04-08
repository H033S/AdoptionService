package com.nazmen.tech.adoption.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AdopterTest {
    
    @Test
    public void createAdopter_ValidationReturnTrue_WithValidObject(){
        Adopter adopter = new Adopter(
            UUID.randomUUID(), 
            "Antonio Nazco", 
            "786-330-3040");

        assertTrue(adopter.isModelValid());
    }

    @Test 
    public void createAdopter_ValidationReturnFalse_WithIncorrectPhoneNumer(){
        Adopter adopter = new Adopter(
            UUID.randomUUID(), 
            "Antonio Nazco", 
            "1232-12321");

        assertFalse(adopter.isModelValid());
        assertEquals(1, adopter.getModelViolations().size());
    }

    @Test 
    public void createAdopter_ValidationReturnFalse_WithIncorrectName(){
        Adopter adopter = new Adopter(
            UUID.randomUUID(), 
            "", 
            "786-768-1191");

        assertFalse(adopter.isModelValid());
        assertEquals(1, adopter.getModelViolations().size());
    }

}
