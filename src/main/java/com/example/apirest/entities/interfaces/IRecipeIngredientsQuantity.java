package com.example.apirest.entities.interfaces;

import com.example.apirest.classes.IngredientsQuantityEntityKey;
import com.example.apirest.classes.Quantity;
import com.example.apirest.entities.IngredientEntity;
import com.example.apirest.entities.RecipeEntity;

public interface IRecipeIngredientsQuantity {

    IngredientsQuantityEntityKey getId();

    RecipeEntity getRecipe();

    IngredientEntity getIngredient();

    Quantity getQuantity();
}
