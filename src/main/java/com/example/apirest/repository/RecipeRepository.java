package com.example.apirest.repository;

import com.example.apirest.entities.RecipeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Integer>{

    /*
     * Le principe de cette recherche est de supprimer tout caractère autre que des lettres, ce qui signifie qu'on retire
     * tous les tirets et/ou espaces qu'il y a dans le nom pour ne garder que chaque mot nécessaire à la recherche. De cette
     * manière, Quatre-quarts et Quatre quart ne pourront pas coexister. Cependant, cette recherche à ses limites et si les
     * mots en base contiennent moins de caractères que ceux à sauvegarder (par exemple en base : Quatre-Quart et à sauver
     * Quatre-quarts, alors les deux coexisteront puisque la recherche incluera une lettre supplémentaire).
     */
    /*@Query("SELECT recipe.id, recipe.name, recipe.preparationTime, recipe.description, ingredient.id, ingredient.name, association.quantity " +
            "FROM RecipeEntity recipe INNER JOIN RecipeIngredientsQuantityEntity association ON recipe.id = association.id.recipeId " +
            "INNER JOIN IngredientEntity ingredient ON association.id.ingredientId = ingredient.id " +
            "WHERE lower(replace(replace(recipe.name, '-', ''), ' ', '')) = lower(replace(replace(:recipeName, '-', ''), ' ', ''))")
    List<Object[]> findByNameLike(@Param("recipeName") String name);*/

    /**
     * Cette requête prend en paramètre un nom dont on sait qu'il peut comporter des lettres, espaces et tirets.
     * Afin de rechercher un nom de recette en base qui pourrait ressembler à celui en paramètre de la méthode,
     * on décide de supprimer tous les tirets et espaces pour enlever ces variables et ne garder que les mots. Si
     * une recette en base contient exactement les mêmes mots, cette requête retournera true. On saura alors qu'une
     * recette contenant ces mots existe déjà.
     * @param name
     * @return
     */
    @Query("SELECT CASE WHEN COUNT(recipe)>0 THEN TRUE ELSE FALSE END FROM RecipeEntity recipe " +
            "WHERE lower(replace(replace(recipe.name, '-', ''), ' ', '')) = lower(replace(replace(:recipeName, '-', ''), ' ', ''))")
    boolean existsByNameLike(@Param("recipeName") String name);

}
