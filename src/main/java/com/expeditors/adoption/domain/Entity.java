package com.expeditors.adoption.domain;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public abstract class Entity implements EntityValidable<Entity> {

    protected static Validator validator;
    
    @NotNull
    protected int id;

    static {
        validator = buildDefaultValidatorFactory().getValidator();
    }

    public Entity(int id) {
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
