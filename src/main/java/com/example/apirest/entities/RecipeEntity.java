package com.example.apirest.entities;

import com.example.apirest.classes.PreparationTime;
import com.example.apirest.entities.interfaces.IRecipe;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "recipe")
public final class RecipeEntity extends AbstractEntity implements Serializable {

    /*
     * The @Column(nullable = false) annotation has no effect if Hibernate doesn’t generate the table
     * definition. That means that you need to pay extra attention to your database script if you create
     * your database schema with Flyway or Liquibase.
     */
    @Column(name = "name")
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "heures", column = @Column(name = "preparation_time_hours")),
            @AttributeOverride( name = "minutes", column = @Column(name = "preparation_time_minutes")),
    })
    private PreparationTime preparationTime;

    @Column(name = "description")
    private String description;

    // cascade = CascadeType.PERSIST : lorsqu'est persisté la recette, l'ingrédient auquel elle est relié est également persisté s'il ne l'est pas encore.
    // On ne le fait pas car risque de créer/modifier des trucs qu'on ne veut pas, donc on fera tout à la main, pareil pour le delete/update en cascade.
    // EAGER: On récupère l'objet entièrement à chaque appel
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<RecipeIngredientsQuantityEntity> ingredients;

    /* CONSTRUCTORS */
    public RecipeEntity() {
        super();
    }

    public RecipeEntity(IRecipe recipe){
        super(recipe.getId());
        this.name = recipe.getName();
        this.preparationTime = recipe.getPreparationTime();
        this.description = recipe.getDescription();
    }

    public RecipeEntity(int id, String name, PreparationTime preparationTime, String description) {
        super(id);
        this.name = name;
        this.preparationTime = preparationTime;
        this.description = description;
    }

    /* GETTER */
    public String getName() { return name; }

    /* SETTER */
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "RecipeEntity{" +  "nom=" + name + ", temps de preparation=" + preparationTime + ", description=" + description + '}';
    }
}
