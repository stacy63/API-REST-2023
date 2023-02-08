package com.example.apirest.dtos;

import com.example.apirest.classes.Quantity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public final class RecipeIngredientsQuantityDto {

    @Positive(message = "RecipeIngredientsQuantityDto.idRecipe must be greater than 1")
    private int idRecipe;
    @Positive(message = "RecipeIngredientsQuantityDto.idIngredient must be greater than 1")
    private int idIngredient;

    @Valid
    private Quantity quantity;

    public RecipeIngredientsQuantityDto() {
    }

    public RecipeIngredientsQuantityDto(int idRecipe, int idIngredient, Quantity quantity) {
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
