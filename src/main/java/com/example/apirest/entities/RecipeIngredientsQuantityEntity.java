package com.example.apirest.entities;

import com.example.apirest.classes.IngredientsQuantityEntityKey;
import com.example.apirest.classes.Quantity;
import com.example.apirest.entities.interfaces.IRecipeIngredientsQuantity;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The entity class which models the join table using the composite key class.
 * @MapsId Signifie que nous lions les champs à une partie de la clé et qu'ils
 * sont les clés étrangères d'une relation plusieurs à un. Nous en avons besoin
 * car, comme nous l'avons mentionné, nous ne pouvons pas avoir d'entités dans
 * la clé composite.
 */
@Entity
@Table(name = "recipe_ingredient_association")
public final class RecipeIngredientsQuantityEntity implements IRecipeIngredientsQuantity, Serializable {

    @EmbeddedId
    private IngredientsQuantityEntityKey id;

    // We configured the relationships to the RecipeEntity and IngredientEntity classes as @ManyToOne. We could do this
    // because with this new entity we structurally decomposed the many-to-many relationship to two many-to-one relationships.
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "quantity")),
            @AttributeOverride(name = "unitOfMeasurement", column = @Column(name = "unit_of_mesurement")),
    })
    private Quantity quantity;

    public RecipeIngredientsQuantityEntity() {
    }

    public RecipeIngredientsQuantityEntity(IngredientsQuantityEntityKey id, Quantity quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public RecipeIngredientsQuantityEntity(IngredientsQuantityEntityKey id, RecipeEntity recipe, IngredientEntity ingredient, Quantity quantity) {
        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    /* GETTER */
    public IngredientsQuantityEntityKey getId() { return id; }

    public RecipeEntity getRecipe() { return recipe; }

    public IngredientEntity getIngredient() { return ingredient; }

    public Quantity getQuantity() { return quantity; }

    /* SETTER */
    public void setId(IngredientsQuantityEntityKey id) { this.id = id; }

    public void setIngredient(IngredientEntity ingredient) { this.ingredient = ingredient; }

    public void setQuantity(Quantity quantity) { this.quantity = quantity; }
}
