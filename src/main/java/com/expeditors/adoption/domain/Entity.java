package com.expeditors.adoption.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

@Setter
@Getter
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

    @JsonIgnore
    public Set<ConstraintViolation<Entity>> getModelViolations(){
        return validator.validate(this);
    }

    @JsonIgnore
    public boolean isModelValid(){
        var modelViolationsFound = getModelViolations().size();
        return modelViolationsFound == 0;
    }
}
