package com.example.apirest.dtos;

import com.example.apirest.classes.AbstractTestBase;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.apirest.classes.ConstantPoolTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class IngredientDtoTests extends AbstractTestBase {

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterAll
    public static void clear() {
        validator = null;
    }

    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenInfZeroId_thenConstraintViolation(){
        //Arrange(s)
        IngredientDto ingredient = new IngredientDto(0, INGREDIENT_NAME);

        //Action
        // Dans le cas où l'ingrédient existe déjà en base
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + ID_NOT_GREATER_THAN_1_ERROR_STRING, constraint.getMessage());
    }

    /* #################  name  ################# */
    @Test
    public void whenNullName_thenConstraintViolation(){
        //Arrange(s)
        IngredientDto ingredient = new IngredientDto(null);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptyName_thenConstraintViolation(){
        //Arrange(s)
        String emptyName = "    ";
        IngredientDto ingredient = new IngredientDto(emptyName);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxChiffreName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecChiffre = "1/2 sel";
        IngredientDto ingredient = new IngredientDto(nameCaracSpecChiffre);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxUnderscoreName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecUnderscore = "chocolat_noir";
        IngredientDto ingredient = new IngredientDto(nameCaracSpecUnderscore);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxDotName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecDot = "Chocolat.";
        IngredientDto ingredient = new IngredientDto(nameCaracSpecDot);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<IngredientDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenGreaterThanZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        IngredientDto ingredient = new IngredientDto(1, INGREDIENT_NAME);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenNewIngredientZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        IngredientDto ingredient = new IngredientDto(0,"Nutella");

        //Action
        // On sait qu'il s'agit d'un nouvel ingredient donc on ne vérifie pas que son id est supérieur à 1 lorsqu'on le reçoit du front
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }

    /* #################  name  ################# */
    @Test
    public void whenCaracSpeciauxDashName_thenNoConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecDash = "demi-sel";
        IngredientDto ingredient = new IngredientDto(nameCaracSpecDash);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenCaracSpeciauxSpaceName_thenNoConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecSpace = "demi sel";
        IngredientDto ingredient = new IngredientDto(nameCaracSpecSpace);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenBasicName_thenNoConstraintViolation(){
        //Arrange(s)
        String ing = INGREDIENT_NAME;
        IngredientDto ingredient = new IngredientDto(ing);

        //Action
        Set<ConstraintViolation<IngredientDto>> constraintViolations = validator.validate(ingredient);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }
}
