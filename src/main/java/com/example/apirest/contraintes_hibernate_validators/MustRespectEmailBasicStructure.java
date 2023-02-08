package com.example.apirest.contraintes_hibernate_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Constraint(validatedBy = MustRespectEmailBasicStructureValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MustRespectEmailBasicStructure {

    String message() default "The email does not respect basic structure";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
