package com.example.apirest.classes;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.apirest.classes.ConstantPoolTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PreparationTimeTests extends AbstractTestBase {

    @BeforeAll
    protected static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterAll
    protected static void clear() { validator = null; }

    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    @Test
    public void whenNegativeHours_thenConstraintViolation(){
        //Arrange(s)
        PreparationTime prep = new PreparationTime(-10, 2);

        //Action
        Set<ConstraintViolation<PreparationTime>> constraintViolations = validator.validate(prep);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<PreparationTime> constraint = constraintViolations.iterator().next();
        assertEquals(constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_IN_THE_HOURS_BOUNDARIES_ERROR, constraint.getMessage());
    }

    @Test
    public void whenGreaterThan24Hours_thenConstraintViolation(){
        //Arrange(s)
        PreparationTime prep = new PreparationTime(30, 2);

        //Action
        Set<ConstraintViolation<PreparationTime>> constraintViolations = validator.validate(prep);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<PreparationTime> constraint = constraintViolations.iterator().next();
        assertEquals(constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_IN_THE_HOURS_BOUNDARIES_ERROR, constraint.getMessage());
    }

    @Test
    public void whenNegativeMinutes_thenConstraintViolation(){
        //Arrange(s)
        PreparationTime prep = new PreparationTime(10, -2);

        //Action
        Set<ConstraintViolation<PreparationTime>> constraintViolations = validator.validate(prep);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<PreparationTime> constraint = constraintViolations.iterator().next();
        assertEquals(constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_IN_THE_MINUTES_BOUNDARIES_ERROR, constraint.getMessage());
    }

    @Test
    public void whenGreaterThan60Minutes_thenConstraintViolation(){
        //Arrange(s)
        PreparationTime prep = new PreparationTime(3, 70);

        //Action
        Set<ConstraintViolation<PreparationTime>> constraintViolations = validator.validate(prep);

        //Asserts
        assertEquals( 1, constraintViolations.size());
        ConstraintViolation<PreparationTime> constraint = constraintViolations.iterator().next();
        assertEquals(constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_IN_THE_MINUTES_BOUNDARIES_ERROR, constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */

    @Test
    public void whenBasicTime_thenNoConstraintViolation(){
        //Arrange(s)
        PreparationTime prep = new PreparationTime(3, 40);

        //Action
        Set<ConstraintViolation<PreparationTime>> constraintViolations = validator.validate(prep);

        //Asserts
        assertEquals( 0, constraintViolations.size());
    }
}
