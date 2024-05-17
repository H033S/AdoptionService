package com.expeditors.adoption.domain;

import java.util.Set;

import com.expeditors.adoption.domain.violations.ConstraintError;

public interface EntityValidable <T> {

    Set<ConstraintError> getModelViolations();
    boolean isModelValid();
}
