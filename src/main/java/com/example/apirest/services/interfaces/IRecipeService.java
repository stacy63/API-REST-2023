package com.example.apirest.services.interfaces;

import com.example.apirest.entities.interfaces.IRecipe;
import com.example.apirest.exceptions.ExistingRecipeException;
import com.example.apirest.exceptions.ValidatorException;
import org.apache.commons.math3.exception.NullArgumentException;

public interface IRecipeService {

    void addRecipe(IRecipe recipe) throws ValidatorException, ExistingRecipeException, NullArgumentException;
}
