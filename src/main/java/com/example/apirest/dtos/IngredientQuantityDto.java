package com.example.apirest.dtos;

import com.example.apirest.classes.Quantity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public final class IngredientQuantityDto {

    /*
     * L'annotation @Valid utilisée sur une dépendance d'un bean permet de demander au moteur de validation de valider aussi la
     * dépendance lors de la validation du bean. Ce mécanisme est récursif : une dépendance annotée avec @Valid peut elle-même
     * contenir des dépendances annotées avec @Valid. Ainsi, l'ensemble des beans dépendants qui seront validés en même temps
     * que le bean est défini en utilisant l'annotation @Valid sur chacune des dépendances concernées. Une dépendance annotée
     * avec @Valid est ignorée par le moteur si sa valeur est nulle.
     */
    @Valid
    @NotNull(message = "IngredientQuantityDto.ingredient cannot be null")
    private IngredientDto ingredient;

    @Valid
    private Quantity quantity;

    /* CONSTRUCTORS */
    public IngredientQuantityDto() {
    }

    public IngredientQuantityDto(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientQuantityDto(IngredientDto ingredient, Quantity quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    /* GETTER */
    public IngredientDto getIngredient() { return ingredient; }

    public Quantity getQuantity() { return quantity; }

    @Override
    public String toString(){
        return "IngredientQuantityDto{" + "ingredient= " + ingredient + ", quantité= " + quantity + '}';
    }
}
