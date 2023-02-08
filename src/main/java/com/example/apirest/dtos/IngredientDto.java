package com.example.apirest.dtos;

import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import com.example.apirest.entities.interfaces.IIngredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import static com.example.apirest.classes.ConstantPool.*;

public final class IngredientDto implements IIngredient {

    /* On utilise un groupe pour cette validation, car elle ne sera vérifiée que lorsque l'on manipulera un dto représentant une
    * entité existante en base, parce que dans le cas où l'entité n'existe pas en base l'id sera par défaut égal à zero */
    @Positive(message = "IngredientDto.id must be greater than 1", groups = AlreadyExistingEntityIdChecks.class)
    private int id;
    @NotBlank(message = "IngredientDto.name may not be blank or null")
    @Pattern(regexp = REGEX_CARAC_SPECIAUX_NOM, message = "IngredientDto.name is not in the expected format")
    private String name;

    /* CONSTRUCTORS */
    public IngredientDto() {
    }

    public IngredientDto(String name) {
        this.name = name;
    }

    public IngredientDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "IngredientDto{" + "id=" + id +  ", nom= " + name + '}';
    }
}
