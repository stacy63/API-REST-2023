package com.example.apirest.contraintes_hibernate_validators;

import com.example.apirest.classes.Quantity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Une dépendance annotée avec @Valid est ignorée par le moteur si sa valeur est nulle. Ainsi, cette validation sera
 * appelée seulement si l'objet Quantity est non null. Ainsi, comme une quantité n'est pas une entité, elle représentera
 * toujours une dépendance donc sera toujours vérifiée bvia un @Valid signifiant qu'aucun null pointeur ne devrait être
 * jeté par les getters.
 */
public final class AllOrNothingNulliltyFieldsValidator implements ConstraintValidator<AllOrNothingNulliltyFields, Quantity> {

    @Override
    public void initialize(AllOrNothingNulliltyFields constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Quantity quantity, ConstraintValidatorContext constraintValidatorContext) {

        // Soit tous les champs sont nuls, soit aucun ne l'est
        if(quantity.getAmount() == 0 ){
            return (quantity.getUnitOfMeasurement() == null);
        }
        return (quantity.getUnitOfMeasurement() != null && quantity.getUnitOfMeasurement().trim() != "");
    }
}
