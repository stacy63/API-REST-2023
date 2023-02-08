package com.example.apirest.dtos;

import com.example.apirest.classes.AbstractTestBase;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import com.example.apirest.contraintes_hibernate_validators.groups.HashProcessChecks;
import com.example.apirest.contraintes_hibernate_validators.groups.UserPasswordEntryChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.apirest.classes.ConstantPoolTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class UserDtoTests extends AbstractTestBase {

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterAll
    public static void clear(){
        validator = null;
    }

    /* ##############################################################################################
     *                                              ERROR
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenLowerThan1Id_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(0, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, USER_LAST_UPDATED_PASSWORD_DATE);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, AlreadyExistingEntityIdChecks.class, HashProcessChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + ID_NOT_GREATER_THAN_1_ERROR_STRING, constraint.getMessage());
    }

    /* #################  email  ################# */
    @Test
    public void whenNullEmail_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(null, USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenMissingATEmail_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto("emailIncorrect.ceu", USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 2, constraintViolations.size() );
        assertTrue( constraintViolations.stream().filter(
                constraintViolationsFound -> constraintViolationsFound.getMessage().equals(
                        constraintViolationsFound.getRootBeanClass().getSimpleName()+
                                "." + constraintViolationsFound.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING)
        ).collect(Collectors.toSet()).size() == 1);
        assertTrue( constraintViolations.stream().filter(
                constraintViolationsFound -> constraintViolationsFound.getMessage().equals("The email does not respect basic structure")
        ).collect(Collectors.toSet()).size() == 1);
    }

    @Test
    public void whenTwoConsecutiveDotEmail_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto("email@g..e", USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenMissingDotEmail_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto("email@hr", USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( "The email does not respect basic structure", constraint.getMessage());
    }

    /* #################  pseudo  ################# */
    @Test
    public void whenNullPseudo_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, null, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptyPseudo_thenConstraintViolation(){
        //Arrange(s)
        String emptyPseudo = "    ";
        UserDto user = new UserDto(USER_EMAIL, emptyPseudo, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenNoAutorizedCaracSpecPseudo_thenConstraintViolation(){
        //Arrange(s)
        String emptyPseudo = "Mon p[eudo ";
        UserDto user = new UserDto(USER_EMAIL, emptyPseudo, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    /* #################  password  ################# */
    @Test
    public void whenNullPassword_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, null);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenEmptyPassword_thenConstraintViolation(){
        //Arrange(s)
        String emptyPassword = "    ";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, emptyPassword);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenHashPasswordInfMinimumSize_thenConstraintViolation(){
        //Arrange(s)
        String userTooSmallPasswordHash = "$argon2id$v=19$m=4096,t=3,p=1$iurr6y6xk2X7X/YVOEQXBg$ti9/b";
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, userTooSmallPasswordHash, USER_LAST_UPDATED_PASSWORD_DATE);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, HashProcessChecks.class, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( "The hash process must have encountered problems", constraint.getMessage());
    }


    @Test
    public void whenEntryPasswordInf12_thenConstraintViolation(){
        //Arrange(s)
        String userSmallPasswordEntry = "User@)09";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, userSmallPasswordEntry);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + " is not long enough", constraint.getMessage());
    }

    @Test
    public void whenEntryPasswordMissingUpperCase_thenConstraintViolation(){
        //Arrange(s)
        String userWithoutLowerCasePasswordEntry = "user_password@)09";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, userWithoutLowerCasePasswordEntry);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenEntryPasswordMissingLowerCase_thenConstraintViolation(){
        //Arrange(s)
        String userWithoutUpperCasePasswordEntry = "USER_PASSWORD@)09";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, userWithoutUpperCasePasswordEntry);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenEntryPasswordMissingEnoughDigits_thenConstraintViolation(){
        //Arrange(s)
        String userWithoutEnoughDigitsPasswordEntry = "User_PASSWORD@)0";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, userWithoutEnoughDigitsPasswordEntry);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    @Test
    public void whenEntryPasswordMissingEnoughSpecialCarac_thenConstraintViolation(){
        //Arrange(s)
        String userWithoutEnoughSpecialCaracPasswordEntry = "UserpASSWORD@09";
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, userWithoutEnoughSpecialCaracPasswordEntry);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_EXPECTED_FORMAT_ERROR_STRING, constraint.getMessage());
    }

    /* #################  pwd_last_update  ################# */
    @Test
    public void whenNullLastUpdate_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, null);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, AlreadyExistingEntityIdChecks.class, HashProcessChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + NOT_NULL_ERROR_FORMAT_STRING, constraint.getMessage());
    }

    @Test
    public void whenFuturLastUpdate_thenConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, LocalDate.of(2023,05,06));

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, AlreadyExistingEntityIdChecks.class, HashProcessChecks.class, Default.class);

        //Asserts
        assertEquals( 1, constraintViolations.size() );
        ConstraintViolation<UserDto> constraint = constraintViolations.iterator().next();
        assertEquals( constraint.getRootBeanClass().getSimpleName()+
                "." + constraint.getPropertyPath() + " cannot be subsequent to the date of the day", constraint.getMessage());
    }

    /* ##############################################################################################
     *                                              OK
     *  ############################################################################################# */

    /* #################  id  ################# */
    @Test
    public void whenGreaterThanZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, USER_LAST_UPDATED_PASSWORD_DATE);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, AlreadyExistingEntityIdChecks.class, HashProcessChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenNewUserZeroId_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        // On sait qu'il s'agit d'un nouvel utilisateur donc on ne vérifie pas que son id est supérieur à 1 lorsqu'on le reçoit du front
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  email  ################# */
    @Test
    public void whenBasicEmail_thenNoConstraintViolation() {
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals(0, constraintViolations.size());
    }

    /* #################  pseudo  ################# */
    @Test
    public void whenBasicPseudo_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  password  ################# */
    @Test
    public void whenBasicEntryPassword_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(USER_EMAIL, USER_PSEUDO, USER_PASSWORD_ENTRY);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void whenBasicHashPassword_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, USER_LAST_UPDATED_PASSWORD_DATE);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, UserPasswordEntryChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

    /* #################  pwd_last_update  ################# */
    @Test
    public void whenBasicLastUpdate_thenNoConstraintViolation(){
        //Arrange(s)
        UserDto user = new UserDto(1, USER_EMAIL, USER_PSEUDO, USER_PASSWORD_HASH, USER_LAST_UPDATED_PASSWORD_DATE);

        //Action
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user, HashProcessChecks.class, AlreadyExistingEntityIdChecks.class, Default.class);

        //Asserts
        assertEquals( 0, constraintViolations.size() );
    }

}
