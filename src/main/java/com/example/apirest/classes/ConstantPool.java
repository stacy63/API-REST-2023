package com.example.apirest.classes;

public abstract class ConstantPool {

    //Intercepte tout ce qui n'est pas uniquement composé d'une lettre majuscule ou minuscule (accentuee ou non) et/ou de tirets et/ou d'espaces ...
    public static final String REGEX_CARAC_SPECIAUX_PSEUDO = "[\\p{L}\\s-_@#0-9\\.{}°&~'^]*";

    //Intercepte tout ce qui ne possède pas au moins 1 majuscule, 1 minuscule, 2 chiffres et 2 caractères spéciaux
    public static final String REGEX_PASSWORD_MINIMUM_CRITERIA = "^(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])(?=(?:\\D*\\d){2})(?=(?:\\w*\\W){2}).*";

    //Intercepte tout ce qui n'est pas uniquement composé d'une lettre majuscule ou minuscule (accentuee ou non) et/ou de tirets et/ou d'espaces
    public static final String REGEX_CARAC_SPECIAUX_NOM = "[\\p{L}\\s-]*";

    //Intercepte tout ce qui n'est pas uniquement composé d'une lettre majuscule ou minuscule (accentuee ou non) et/ou de tirets et/ou d'espaces et/ou de chiffres et/ou de points
    public static final String REGEX_CARAC_SPECIAUX_TEXTE = "[\\p{L}-\\s0-9\\.]*";
}
