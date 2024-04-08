package com.nazmen.tech.adoption.domain.entities;

import java.util.UUID;

import com.nazmen.tech.adoption.domain.Entity;
import com.nazmen.tech.adoption.domain.annottations.PhoneNumberPattern;

import jakarta.validation.constraints.Size;

public class Adopter extends Entity {
    
    @Size(min = 2, message = "{validation.adopter.name.size.too_short}")
    @Size(max = 200, message = "{validation.adopter.name.size.too_long}")
    private String adopterName;

    @PhoneNumberPattern
    private String phoneNumber;

    public Adopter(
        UUID id,
        String adopterName, 
        String phoneNumber) {
        
            super(id);
            this.adopterName = adopterName;
            this.phoneNumber = phoneNumber;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
