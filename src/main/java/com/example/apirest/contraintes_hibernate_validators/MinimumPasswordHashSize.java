package com.example.apirest.contraintes_hibernate_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = MinimumPasswordHashSizeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MinimumPasswordHashSize {

    String message() default "The hash process must have encountered problems";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
