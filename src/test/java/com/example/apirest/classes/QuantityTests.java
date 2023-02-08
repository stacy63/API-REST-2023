package com.example.apirest.classes;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.apirest.classes.ConstantPoolTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class QuantityTests extends AbstractTestBase {

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterAll
    public static void clear() {
        validator = null;
    }

    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    @Test
    public void whenAmountQuantityNullField_thenConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(0, UNIT_OF_MESUREMENT);

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<Quantity> constraint = constraintViolations.iterator().next();
        assertEquals(FIELDS_PARTIAL_NULLITY_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenUnitQuantityNullFields_thenConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(AMOUNT, null);

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<Quantity> constraint = constraintViolations.iterator().next();
        assertEquals( "All fields must be either null or non-null", constraint.getMessage());
    }

    @Test
    public void whenUnitQuantityEmptyFields_thenConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(AMOUNT, "   ");

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<Quantity> constraint = constraintViolations.iterator().next();
        assertEquals( "All fields must be either null or non-null", constraint.getMessage());
    }

    @Test
    public void whenAmountNegative_thenConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(-2, "g");

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<Quantity> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + " must be positive", constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */

    @Test
    public void whenBasicQuantity_thenNoConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(AMOUNT, UNIT_OF_MESUREMENT);

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }

    @Test
    public void whenAllQuantityFieldsAreNull_thenNoConstraintViolation(){
        //Arrange(s)
        Quantity quant = new Quantity(0, null);

        //Action
        Set<ConstraintViolation<Quantity>> constraintViolations = validator.validate(quant);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }
}
