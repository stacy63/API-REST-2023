package com.example.apirest.services;

import com.example.apirest.classes.IngredientsQuantityEntityKey;
import com.example.apirest.dtos.IngredientQuantityDto;
import com.example.apirest.entities.IngredientEntity;
import com.example.apirest.entities.RecipeEntity;
import com.example.apirest.entities.RecipeIngredientsQuantityEntity;
import com.example.apirest.entities.interfaces.IRecipe;
import com.example.apirest.exceptions.ExistingRecipeException;
import com.example.apirest.exceptions.ValidatorException;
import com.example.apirest.repository.IngredientRepository;
import com.example.apirest.repository.RecipeIngredientsQuantityRepository;
import com.example.apirest.repository.RecipeRepository;
import com.example.apirest.services.interfaces.IRecipeService;
import jakarta.validation.ConstraintViolation;
import org.apache.commons.math3.exception.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public final class RecipeService extends AbstractService implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientsQuantityRepository recipeIngredientsQuantityRepository;

    /**
     * On récupère du front les infos renseignées par l'utilisateur dans un RecipeDto et on renvoit le recipeDto du RecipeEntity saved en bdd.
     *
     * @param recipeDto
     * @return
     */
    public void addRecipe(IRecipe recipeDto) throws ValidatorException, ExistingRecipeException {
        if(recipeDto == null){
            throw new NullArgumentException();
        }
        /* On effectue la validation avec le groupe Default car dans ce cas on sait que l'id est égal à zero puisqu'il s'agit de
         * la création d'une nouvelle recette en base. */
        Set<ConstraintViolation<IRecipe>> constraintViolations = validator.validate(recipeDto);
        // Si une à plusieurs contraintes a été violées le recipe dto n'est pas valide donc aucune opération ne pourra être effectuée en base pour cette recette
        if(constraintViolations.size() > 0){
            throw new ValidatorException("Recette invalide");
        }
        /* Via les restrictions sur les noms des recettes, on sait qu'elles contiennent uniquement des lettres, des espaces et des tirets.
         * On décide donc de supprimer tous les tirets et espace présent dans le nom pour ne garder que les mots et chercher si la recette existe déjà en base.*/
        boolean doesRecipeAlreadyExists = recipeRepository.existsByNameLike(recipeDto.getName());
        if(doesRecipeAlreadyExists){
            throw new ExistingRecipeException("Cette recette a déjà été créée");
        }
        // La recette n'existant pas on peut la créer
        RecipeEntity recipe = recipeRepository.save(new RecipeEntity(recipeDto));
        /* On créé chacun des ingrédients de la recette qui n'existe pas encore en base. Ensuite on ajoute l'association de chacun de ces ingrédients à la recette
         * dans la table des associations en y précisant la quantité. */
        for(IngredientQuantityDto ingredientWithQuantity : recipeDto.getIngredients()){
            IngredientEntity ingredient = null;
            // Via la validation, on sait qu'aucun des ingrédients n'est null ainsi que leur nom
            Optional<IngredientEntity> ingredientFound = ingredientRepository.findByNameLike(ingredientWithQuantity.getIngredient().getName());
            if(ingredientFound.isPresent()){
                ingredient = ingredientFound.get();
            } else {
                ingredient = ingredientRepository.save(new IngredientEntity(ingredientWithQuantity.getIngredient()));
            }
            IngredientsQuantityEntityKey associationKey = new IngredientsQuantityEntityKey(recipe.getId(), ingredient.getId());
            RecipeIngredientsQuantityEntity recipeIngredientsQuantity = new RecipeIngredientsQuantityEntity(associationKey, recipe, ingredient, ingredientWithQuantity.getQuantity());
            recipeIngredientsQuantityRepository.save(recipeIngredientsQuantity);
        }
    }
}
