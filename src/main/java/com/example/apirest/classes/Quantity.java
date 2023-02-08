package com.example.apirest.classes;

import com.example.apirest.contraintes_hibernate_validators.AllOrNothingNulliltyFields;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

import java.io.Serializable;

/**
 * On autorise la création d'un Quantity avec tous ses champs null car un quantity est optionel
 */
@AllOrNothingNulliltyFields
@Embeddable
public final class Quantity implements Serializable {

    @Min(value = 0, message = "Quantity.amount must be positive")
    private int amount;

    private String unitOfMeasurement;

    /* CONSTRUCTOR */
    public Quantity() {
    }

    public Quantity(int amount, String unitOfMeasurement) {
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /* GETTER */
    public int getAmount() { return amount; }

    public String getUnitOfMeasurement() { return unitOfMeasurement; }

    @Override
    public String toString(){
        return "Quantity{" + "amount= " + amount + unitOfMeasurement + '}';
    }
}
