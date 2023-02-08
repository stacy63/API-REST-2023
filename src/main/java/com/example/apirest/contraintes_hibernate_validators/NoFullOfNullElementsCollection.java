package com.example.apirest.contraintes_hibernate_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.*;

@Constraint(validatedBy = NoFullOfNullElementsCollectionValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoFullOfNullElementsCollection {

    String message() default "The collection must not be full of null elements";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
