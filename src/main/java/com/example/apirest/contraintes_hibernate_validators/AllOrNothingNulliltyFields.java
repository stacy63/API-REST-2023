package com.example.apirest.contraintes_hibernate_validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = AllOrNothingNulliltyFieldsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllOrNothingNulliltyFields {

    String message() default "All fields must be either null or non-null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
