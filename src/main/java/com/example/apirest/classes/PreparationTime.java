package com.example.apirest.classes;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.io.Serializable;

/**
 * Objet embarquable. Cette classe permet d'embarquer le temps de préparation dans l'entité recette en mappant les champs
 * heures et minutes dans les colonnes de la table des recettes associées. Cette classe n'est pas une entité on ne peut donc
 * pas faire de requêtes dessus et elle possède la même clé primaire que la table recette. Tout est regroupé dans cette table.
 * Le mapping est configuré dans l'entité recette sur le champ preparationTime.
 */
@Embeddable
public final class PreparationTime implements Serializable {

    @Min(value = 0, message = "PreparationTime.heures must be between 0 and 24")
    @Max(value = 24, message = "PreparationTime.heures must be between 0 and 24")
    private int heures;

    @Min(value = 0, message = "PreparationTime.minutes must be between 0 and 60")
    @Max(value = 60, message = "PreparationTime.minutes must be between 0 and 60")
    private int minutes;

    public PreparationTime() {
    }

    public PreparationTime(int heures, int minutes) {
        this.heures = heures;
        this.minutes = minutes;
    }

    /* GETTER */
    public int getHeures() { return heures; }

    public int getMinutes() { return minutes; }
}
