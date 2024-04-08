package com.nazmen.tech.adoption.domain;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;

public abstract class Entity implements EntityValidable<Entity> {

    protected static Validator validator;
    
    @NotNull
    protected UUID id;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Entity(UUID id) {
        this.id = id;
    }

    public Set<ConstraintViolation<Entity>> getModelViolations(){
        return validator.validate(this);
    }

    public boolean isModelValid(){
        getModelViolations().forEach(System.out::println);
        var modelViolationsFound = getModelViolations().size();
        return modelViolationsFound == 0;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
