package com.expeditors.adoption.domain;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public interface EntityValidable <T extends Entity> {
    
    Set<ConstraintViolation<T>> getModelViolations();
    boolean isModelValid();
}
