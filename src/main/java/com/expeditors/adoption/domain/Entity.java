package com.expeditors.adoption.domain;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.expeditors.adoption.domain.violations.ConstraintError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
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
    public Set<ConstraintError> getModelViolations(){

        return validator.validate(this)
                .stream()
                .map(violation -> ConstraintError.builder()
                        .message(violation.getMessage())
                        .fieldName(violation.getPropertyPath().toString())
                        .rejectedValue(Objects.isNull(violation.getInvalidValue())?"null":violation.getInvalidValue().toString())
                        .build())
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public boolean isModelValid(){
        var modelViolationsFound = getModelViolations().size();
        return modelViolationsFound == 0;
    }
}
