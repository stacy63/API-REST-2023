package com.example.apirest.dtos;

import com.example.apirest.classes.PreparationTime;
import com.example.apirest.contraintes_hibernate_validators.NoFullOfNullElementsCollection;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import com.example.apirest.entities.interfaces.IRecipe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Set;

import static com.example.apirest.classes.ConstantPool.*;

/**
 * Note: Data Transfer Objects are not meant for injection. They are used for transferring data from one
 * layer to another. The response through DTOs may be same or different based on the APIs present in your
 * application. So, it is better to create instance of DTO when required to send data from one layer to
 * another. So, it is not recommend to annotate DTO with @Component based on the working definition of DTO.
 */
public final class RecipeDto implements IRecipe {

    /* On utilise un groupe pour cette validation, car elle ne sera vérifiée que lorsque l'on manipulera un dto représentant une
     * entité existante en base, parce que dans le cas où l'entité n'existe pas en base l'id sera par défaut égal à zero. */
    @Positive(message = "RecipeDto.id must be greater than 1", groups = AlreadyExistingEntityIdChecks.class)
    private int id;

    @NotBlank(message = "RecipeDto.name may not be blank or null")
    @Pattern(regexp = REGEX_CARAC_SPECIAUX_NOM, message = "RecipeDto.name is not in the expected format")
    private String name;
    @Valid
    private PreparationTime preparationTime;
    @NotBlank(message = "RecipeDto.description may not be blank or null")
    @Pattern(regexp = REGEX_CARAC_SPECIAUX_TEXTE, message = "RecipeDto.description is not in the expected format")
    private String description;

    // A field (e.g. CharSequence, Collection, Map, or Array) constrained with @NotEmpty must be not null, and its size/length must be greater than zero
    // Si une telle collection est marquée avec l'annotation @Valid, alors toutes les occurrences de la collection seront validées lorsque le bean qui encapsule la collection sera validé.
    @NotEmpty(message = "A recipe must not be empty of ingredients")
    @NoFullOfNullElementsCollection
    @Valid
    private Set<IngredientQuantityDto> ingredients;

    /* CONSTRUCTORS */
    public RecipeDto() {
    }

    public RecipeDto(int id, String name, PreparationTime preparationTime, String description, Set<IngredientQuantityDto> ingredients) {
        this.id = id;
        this.name = name;
        this.preparationTime = preparationTime;
        this.description = description;
        this.ingredients = ingredients;
    }

    public RecipeDto(String name, PreparationTime preparationTime, String description, Set<IngredientQuantityDto> ingredients) {
        this.name = name;
        this.preparationTime = preparationTime;
        this.description = description;
        this.ingredients = ingredients;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PreparationTime getPreparationTime() {
        return preparationTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Set<IngredientQuantityDto> getIngredients() { return ingredients; }

    @Override
    public String toString(){
        return "RecipeDto{" + "id=" + id + ", nom= " + name + ", temps de préparation= " + preparationTime.getHeures() + "h" + preparationTime.getMinutes() + "min, étapes = " + description + ", quantité d'ingredients=" + ingredients + '}';
    }
}
