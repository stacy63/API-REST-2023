package com.example.apirest.classes;

import java.time.LocalDate;

public abstract class ConstantPoolTestBase {

    /* ##############################################################################################
     *                                              QUANTITY
     *  ############################################################################################# */

    public static final Integer AMOUNT = 240;

    public static final String UNIT_OF_MESUREMENT = "g";

    public static final String FIELDS_PARTIAL_NULLITY_ERROR_STRING = "All fields must be either null or non-null";

    /* ##############################################################################################
     *                                          PREPARATION TIME
     *  ############################################################################################# */

    public static final String NOT_IN_THE_HOURS_BOUNDARIES_ERROR = " must be between 0 and 24";
    public static final String NOT_IN_THE_MINUTES_BOUNDARIES_ERROR = " must be between 0 and 60";

    /* ##############################################################################################
     *                                              INGREDIENT
     *  ############################################################################################# */

    public static final String INGREDIENT_NAME = "Ravioles";

    /* ##############################################################################################
     *                                              RECIPE
     *  ############################################################################################# */

    public static final String RECIPE_NAME = "Ravioli";
    public static final String RECIPE_DESCRIPTION = "Ici sont décrites les étapes de la recette du 4-quarts à suivre";
    public static final String RECIPE_NOT_EMPTY_OF_INGREDIENT_ERROR_STRING = "A recipe must not be empty of ingredients";

    /* ##############################################################################################
     *                                              USER
     *  ############################################################################################# */

    public static final String NOT_NULL_ERROR_FORMAT_STRING = " may not be null";
    public static final String USER_EMAIL = "e-mai|.09@gO8-i.e";
    public static final String USER_PSEUDO = "#u-ser_P. seudo@08{}°&~'^";
    public static final String USER_PASSWORD_ENTRY = "IlS'agitDumot de passe@User!09";
    public static final String USER_PASSWORD_HASH = "$argon2id$v=19$m=4096,t=3,p=1$iurr6y6xk2X7X/YVOEQXBg$ti9/be9VgbXtJWpm1hoYyLm8V0wBGr+dxu9X+PFbpZI";
    public static final LocalDate USER_LAST_UPDATED_PASSWORD_DATE = LocalDate.of(2022, 12, 25);

    /* ##############################################################################################
     *                                              COMMON
     *  ############################################################################################# */

    public static final String NOT_BLANK_OR_NULL_ERROR_FORMAT_STRING = " may not be blank or null";
    public static final String NOT_EXPECTED_FORMAT_ERROR_STRING = " is not in the expected format";

    public static final String ID_NOT_GREATER_THAN_1_ERROR_STRING = " must be greater than 1";
}