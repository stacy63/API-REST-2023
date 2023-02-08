package com.example.apirest.contraintes_hibernate_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public final class MustRespectEmailBasicStructureValidator implements ConstraintValidator<MustRespectEmailBasicStructure, String> {

    @Override
    public void initialize(MustRespectEmailBasicStructure constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email == null){
            // On renvoie true pour déléguer la tâche à l'annotation @NotNull qui renverra le bon message
            return true;
        }
        return Pattern.matches(".*@.*\\..*", email);
    }
}
