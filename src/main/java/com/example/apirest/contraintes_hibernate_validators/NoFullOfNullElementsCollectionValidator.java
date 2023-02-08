package com.example.apirest.contraintes_hibernate_validators;

import com.example.apirest.dtos.IngredientQuantityDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;

public final class NoFullOfNullElementsCollectionValidator implements ConstraintValidator<NoFullOfNullElementsCollection, Set<IngredientQuantityDto>> {

    @Override
    public void initialize(NoFullOfNullElementsCollection constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Set<IngredientQuantityDto> ingredientQuantityDtos, ConstraintValidatorContext constraintValidatorContext) {
        if(ingredientQuantityDtos == null || ingredientQuantityDtos.size() == 0){
            return true;
        }
        return (ingredientQuantityDtos.stream().filter( ingredientQuantityDto -> ingredientQuantityDto != null).collect(Collectors.toSet()).size() > 0 );
    }
}
