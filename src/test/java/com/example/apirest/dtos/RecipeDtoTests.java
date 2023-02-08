package com.example.apirest.dtos;

import com.example.apirest.classes.AbstractTestBase;
import com.example.apirest.classes.PreparationTime;
import com.example.apirest.classes.Quantity;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.apirest.classes.ConstantPoolTestBase.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pour valider les contraintes sur les données d'un bean, il faut obtenir une instance de l'interface Validator,
 * utiliser cette instance pour valider les données d'un bean. Les éventuelles erreurs détectées par cette validation
 * sont retournées sous la forme d'un Set d'objets de type ConstraintViolation. Il existe plusieurs méthodes de validation
 * pour le validator. La validation des dépendances déclarées avec l'annotation @Valid n'est effective qu'avec la méthode
 * validate().
 */
public final class RecipeDtoTests extends AbstractTestBase {

    private static PreparationTime preparationTime;
    private static IngredientDto ingredientDto;
    private static Quantity quantity;
    private static IngredientQuantityDto ingredientQuantityDto;
    private static Set<IngredientQuantityDto> ingredients;


    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        preparationTime = new PreparationTime(1,45);
        ingredientDto = new IngredientDto(1,"Pâte feuilletée");
        quantity = new Quantity(230,"g");
        ingredientQuantityDto = new IngredientQuantityDto(ingredientDto, quantity);
        ingredients = new HashSet<>(Arrays.asList(ingredientQuantityDto));
    }

    @AfterAll
    public static void clear(){
        validator = null;
        preparationTime = null;
        ingredientDto = null;
        quantity = null;
        ingredientQuantityDto = null;
        ingredients = null;
    }


    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenLowerThan1Id_thenConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(0, RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + ID_NOT_GREATER_THAN_1_ERROR_STRING, constraint.getMessage());
    }

    /* #################  name  ################# */
    @Test
    public void whenNullName_thenConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(1,null, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptyName_thenConstraintViolation(){
        //Arrange(s)
        String emptyString = "    ";
        RecipeDto recipe = new RecipeDto(emptyString, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxChiffreName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecChiffre = "4 quarts";
        RecipeDto recipe = new RecipeDto(nameCaracSpecChiffre, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxUnderscoreName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecUnderscore = "Ravioli_bolognaise";
        RecipeDto recipe = new RecipeDto(nameCaracSpecUnderscore, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxDotName_thenConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecDot = "Ravioli.";
        RecipeDto recipe = new RecipeDto(nameCaracSpecDot, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    /* #################  preparationTime  ################# */

    /*
     * Ce test est ici uniquement pour vérifier que le @Valid du RecipeDto sur le champ preparationTime fonctionne, car ce test
     * fait la validation de la négativité de l'heure d'un PreparationTime donc fait normalement partie d'un test du PreparationTime.
     */
    @Test
    public void whenNegativeTime_thenConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, new PreparationTime(-10, 0), RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( "PreparationTime.heures must be between 0 and 24", constraint.getMessage());
    }

    /* #################  description  ################# */
    @Test
    public void whenNullDesc_thenConstraintViolation(){
        //Arrange(s)
        String emptyString = "    ";
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, emptyString, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptyDesc_thenConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, null, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenCaracSpeciauxUnderscoreDesc_thenConstraintViolation(){
        //Arrange(s)
        String descCaracSpecUnderscore = "demi_sel";
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, descCaracSpecUnderscore, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    /* #################  ingredients  ################# */
    @Test
    public void whenNullSetOfIngredients_thenConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, null);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals(RECIPE_NOT_EMPTY_OF_INGREDIENT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptySetOfIngredients_thenConstraintViolation(){
        //Arrange(s)
        Set<IngredientQuantityDto> setOfIng = new HashSet<>();
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, setOfIng);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals(RECIPE_NOT_EMPTY_OF_INGREDIENT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenSetOfNullIngredientQuantity_thenConstraintViolation(){
        //Arrange(s)
        Set<IngredientQuantityDto> setOfIng = new HashSet<>();
        setOfIng.add(null);
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, setOfIng);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( "The collection must not be full of null elements", constraint.getMessage());

    }

    /*
     * Ce test est ici uniquement pour vérifier que le @Valid du RecipeDto sur le champ ingredients fonctionne, car ce test
     * fait la validation de la nullité de l'ingrédient d'un IngredientQuantityDto donc fait normalement partie d'un test du IngredientQuantityDtoTest.
     */
    @Test
    public void whenSetOfNullIngredient_thenConstraintViolation(){
        //Arrange(s)
        IngredientDto ing = null;
        IngredientQuantityDto ingredientQuantityDto = new IngredientQuantityDto(ing, quantity);
        Set<IngredientQuantityDto> setOfIng = new HashSet<>();
        setOfIng.add(ingredientQuantityDto);
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, setOfIng);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( "IngredientQuantityDto.ingredient cannot be null", constraint.getMessage());
    }

    /*
     * Ce test est ici uniquement pour vérifier que le @Valid 2 niveau plus bas pour le IngredientQuantityDto sur le champ ingredient fonctionne, car ce test
     * fait la validation du nom d'un IngredientDto donc fait normalement partie d'un test du IngredientDtoTest.
     */
    @Test
    public void whenSetOfIngredientNameNull_thenConstraintViolation(){
        //Arrange(s)
        IngredientDto ing = new IngredientDto(null);
        IngredientQuantityDto ingredientQuantityDto = new IngredientQuantityDto(ing, quantity);
        Set<IngredientQuantityDto> setOfIng = new HashSet<>();
        setOfIng.add(ingredientQuantityDto);
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, setOfIng);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<RecipeDto> constraint = constraintViolations.iterator().next();
        assertEquals( "IngredientDto.name may not be blank or null", constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenGreaterThanZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(1, RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenNewRecipeZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(0, RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        // On sait qu'il s'agit d'une nouvelle recette donc on ne vérifie pas que son id est supérieur à 1 lorsqu'on la reçoit du front
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  name  ################# */
    @Test
    public void whenCaracSpeciauxDashName_thenNoConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecDash = "Quatre-quarts";
        RecipeDto recipe = new RecipeDto(nameCaracSpecDash, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenCaracSpeciauxSpaceName_thenNoConstraintViolation(){
        //Arrange(s)
        String nameCaracSpecSpace = "Quatre quarts";
        RecipeDto recipe = new RecipeDto(nameCaracSpecSpace, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenBasicName_thenNoConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  description  ################# */
    @Test
    public void whenDescContainsDot_thenNoConstraintViolation(){
        //Arrange(s)
        String descCaracSpecDot = "phrase.";
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, descCaracSpecDot, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenDescContainsDash_thenNoConstraintViolation(){
        //Arrange(s)
        String descCaracSpecDash = "demi-sel";
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, descCaracSpecDash, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenBasicDesc_thenNoConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  ingredients  ################# */
    @Test
    public void whenBasicSetOfIngredients_thenNoConstraintViolation(){
        //Arrange(s)
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, ingredients);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenNoFullOfNullIngredientQuantitySet_thenNoConstraintViolation(){
        //Arrange(s)
        Set<IngredientQuantityDto> setOfIng = new HashSet<>();
        setOfIng.add(ingredientQuantityDto);
        setOfIng.add(null);
        RecipeDto recipe = new RecipeDto(RECIPE_NAME, preparationTime, RECIPE_DESCRIPTION, setOfIng);

        //Action
        Set<ConstraintViolation<RecipeDto>> constraintViolations = validator.validate(recipe);

        //Asserts
        assertEquals( 0, constraintViolations.size() );

    }
}
