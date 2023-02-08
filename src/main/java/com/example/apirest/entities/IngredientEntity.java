package com.example.apirest.entities;

import com.example.apirest.entities.interfaces.IIngredient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public final class IngredientEntity extends AbstractEntity implements IIngredient, Serializable {

    /*
     * The @Column(nullable = false) annotation has no effect if Hibernate doesn’t generate the table
     * definition. That means that you need to pay extra attention to your database script if you create
     * your database schema with Flyway or Liquibase. En effet, la constraint "NOT NULL" est présente dans
     * le script donc pas besoin du nullable ici.
     */
    @Column(name= "name")
    private String name;

    //cascade = CascadeType.PERSIST, on ne le fait pas car risque de créer/modifier des trucs qu'on ne veut pas, donc on fera tout à la main pareil pour le delete/update en cascade.
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.EAGER)
    private Set<RecipeIngredientsQuantityEntity> quantitiesForEachRecipe;

    /* CONSTRUCTORS */
    public IngredientEntity(){
        super();
    }

    public IngredientEntity(IIngredient ingredient){
        super(ingredient.getId());
        this.name = ingredient.getName();
    }

    public IngredientEntity(int id, String name) {
        super(id);
        this.name = name;
    }

    /* GETTER */
    public String getName() { return name; }

    /* SETTER */
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "IngredientEntity{" +  "nom=" + name + '}';
    }
}
