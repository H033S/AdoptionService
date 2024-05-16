package com.expeditors.adoption.domain.entities;

import com.expeditors.adoption.domain.AbstractEntity;
import com.expeditors.adoption.domain.annottations.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class Adopter extends AbstractEntity {
    
    @Size(min = 2, message = "{validation.adopter.name.size.too_short}")
    @Size(max = 200, message = "{validation.adopter.name.size.too_long}")
    @Column(
            name = "adopter_name",
            nullable = false
    )
    private String adopterName;

    @PhoneNumber(message = "{validation.adopter.phone-number.incorrect}")
    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;


    public Adopter(
        int id,
        String adopterName, 
        String phoneNumber) {
        
            super(id);
            this.adopterName = adopterName;
            this.phoneNumber = phoneNumber;
    }

    public Adopter() {

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

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", adopterName='" + adopterName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
