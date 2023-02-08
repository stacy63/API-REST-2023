package com.example.apirest.entities.interfaces;

import com.example.apirest.classes.PreparationTime;
import com.example.apirest.dtos.IngredientQuantityDto;

import java.util.Set;

public interface IRecipe {

    int getId();

    String getName();

    PreparationTime getPreparationTime();

    String getDescription();

    Set<IngredientQuantityDto> getIngredients();
}
