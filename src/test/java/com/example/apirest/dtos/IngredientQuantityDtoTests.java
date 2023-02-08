package com.example.apirest.dtos;

import com.example.apirest.classes.AbstractTestBase;
import com.example.apirest.classes.Quantity;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class IngredientQuantityDtoTests extends AbstractTestBase {

    private static Quantity quantity;

    private static IngredientDto ingredientDto;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        quantity = new Quantity(230,"g");
        ingredientDto = new IngredientDto(2, "Chocolat");
    }

    @AfterAll
    public static void clear() {
        validator = null;
        quantity = null;
        ingredientDto = null;
    }

    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    /* #################  ingredient  ################# */
    @Test
    public void whenNullIngredient_thenConstraintViolation(){
        //Arrange(s)
        IngredientQuantityDto ingredientQuantity = new IngredientQuantityDto(null, quantity);

        //Action
        Set<ConstraintViolation<IngredientQuantityDto>> constraintViolations = validator.validate(ingredientQuantity, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<IngredientQuantityDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + " cannot be null", constraint.getMessage());
    }

    /* #################  quantity  ################# */
    /*
     * Ce test est ici uniquement pour vérifier que le @Valid du IngredientQuantityDto sur le champ quantity fonctionne, car ce test
     * fait la validation de la nullité partielle des champs d'une Quantity donc fait normalement partie d'un test du QuantityTest.
     */
    @Test
    public void whenSomeQuantityNullFields_thenConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(0, "g");
        IngredientQuantityDto ingredientQuantity = new IngredientQuantityDto(ingredientDto, quant);

        //Action
        Set<ConstraintViolation<IngredientQuantityDto>> constraintViolations = validator.validate(ingredientQuantity);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<IngredientQuantityDto> constraint = constraintViolations.iterator().next();
        assertEquals( "All fields must be either null or non-null", constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */


    /* #################  ingredient  ################# */
    @Test
    public void whenBasicIngredient_thenNoConstraintViolation(){
        //Arrange(s)
        IngredientQuantityDto ingredientQuantity = new IngredientQuantityDto(ingredientDto, quantity);

        //Action
        Set<ConstraintViolation<IngredientQuantityDto>> constraintViolations = validator.validate(ingredientQuantity, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }

    @Test
    public void whenNewIngredientZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        IngredientQuantityDto ingredientQuantity = new IngredientQuantityDto(new IngredientDto(0,"Nutella"), quantity);

        //Action
        // On sait qu'il s'agit d'un nouvel ingredient donc on ne vérifie pas que son id est supérieur à 1 lorsqu'on le reçoit du front
        Set<ConstraintViolation<IngredientQuantityDto>> constraintViolations = validator.validate(ingredientQuantity);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }

    /*
     * Ce test est ici uniquement pour vérifier que le @Valid du IngredientQuantityDto sur le champ quantity fonctionne, car ce test
     * fait la validation de la nullité d'une Quantity donc fait normalement partie d'un test du QuantityTest.
     */
    @Test
    public void whenNullQuantity_thenNoConstraintViolation(){
        //Arrange(s)
        IngredientQuantityDto ingredientQuantity = new IngredientQuantityDto(ingredientDto, null);

        //Action
        Set<ConstraintViolation<IngredientQuantityDto>> constraintViolations = validator.validate(ingredientQuantity, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }

}
