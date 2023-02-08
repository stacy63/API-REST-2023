package com.example.apirest.controllers;

import com.example.apirest.dtos.RecipeDto;
import com.example.apirest.exceptions.ExistingRecipeException;
import com.example.apirest.exceptions.ValidatorException;
import com.example.apirest.services.interfaces.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/recette")
public final class RecipeController {

    @Autowired
    private IRecipeService iRecipeService;

    @PostMapping
    public ResponseEntity addRecipe(@RequestBody RecipeDto recipeDto) throws ValidatorException, ExistingRecipeException {
        iRecipeService.addRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
