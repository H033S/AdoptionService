package com.expeditors.adoption.domain.annottations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;


@Target({ 
    ElementType.FIELD, 
    ElementType.PARAMETER }
    )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(
    regexp = "^\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}$",
    message = "{validation.phoneNumber.format.invalid}") 
public @interface PhoneNumberPattern {    

    String message() default "";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}