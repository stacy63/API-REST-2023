package com.example.apirest.repository;

import com.example.apirest.entities.RecipeIngredientsQuantityEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientsQuantityRepository extends CrudRepository<RecipeIngredientsQuantityEntity, Integer> {
}
