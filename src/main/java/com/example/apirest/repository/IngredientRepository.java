package com.example.apirest.repository;

import com.example.apirest.entities.IngredientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, Integer> {

    @Query("SELECT ingredient FROM IngredientEntity ingredient " +
            "WHERE lower(replace(replace(ingredient.name, '-', ''), ' ', '')) = lower(replace(replace(:ingredientName, '-', ''), ' ', ''))")
    Optional<IngredientEntity> findByNameLike(@Param("ingredientName") String name);

    @Query("SELECT CASE WHEN COUNT(ingredient)>0 THEN TRUE ELSE FALSE END FROM IngredientEntity ingredient " +
            "WHERE lower(replace(replace(ingredient.name, '-', ''), ' ', '')) = lower(replace(replace(:ingredientName, '-', ''), ' ', ''))")
    boolean existsByNameLike(@Param("ingredientName") String name);
}
