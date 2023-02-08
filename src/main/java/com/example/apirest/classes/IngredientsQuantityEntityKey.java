package com.example.apirest.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * We need to store the quantity of an ingredient in a recipe. This is a situation when the relationship itself
 * has an attribute. Thus, we have to attach a quantity attribute to the join table. Since we map DB attributes
 * to class fields in JPA, we need to create a new entity class for the relationship. Of course, every JPA entity
 * needs a primary key. Because our primary key is a composite key [Signifie que la clé possède plusieurs champs]
 * (recipe_id, ingredient_id), we have to create a new class that will hold the different parts of the key. Note
 * that a composite key class has to fulfill some key requirements:
 *  - We have to mark it with @Embeddable.
 *  - It has to implement java.io.Serializable.
 *  - We need to provide an implementation of the hashcode() and equals() methods.
 *  - None of the fields can be an entity themselves
 *  - have a public no-arg constructor
 *
 *  Using this composite key class, we can then create the entity class, which models the join table. @Embeddable
 *  signifie que la classe peut être embarqué dans une autre classe.
 */
@Embeddable
public final class IngredientsQuantityEntityKey implements Serializable {

    @Column(name = "recipe_id")
    int recipeId;

    @Column(name = "ingredient_id")
    int ingredientId;

    public IngredientsQuantityEntityKey() {
    }

    public IngredientsQuantityEntityKey(int recipeId, int ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o == null)
            return false;
        if(getClass() != o.getClass())
            return false;

        IngredientsQuantityEntityKey other = (IngredientsQuantityEntityKey) o;
        return Objects.equals(recipeId, other.recipeId) && Objects.equals(ingredientId, other.ingredientId);
    }

    /**
     * Il est nécessaire de redéfinir la méthode hashCode() si la méthode equals() est redéfinie car il faut respecter
     * le contrat qui précise que deux objets égaux doivent avoir le même hashcode. Généralement, la redéfinition de la
     * méthode equals() utilise tout ou partie des attributs de la classe pour tester l'égalité de deux objets. La
     * problématique est que la valeur de retour de la méthode hashCode() est de type int : il est donc nécessaire
     * d'appliquer un algorithme qui va déterminer une valeur de type int à partir des champs à utiliser. Il est nécessaire
     * que cet algorithme assure que la valeur de hachage calculée soit toujours la même avec les mêmes attributs.
     * Généralement, cet algorithme calcule une valeur de type int pour chaque attributs et combine ces valeurs en utilisant
     * un multiplicateur (généralement un nombre premier) pour déterminer la valeur de hachage.
     */
    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
