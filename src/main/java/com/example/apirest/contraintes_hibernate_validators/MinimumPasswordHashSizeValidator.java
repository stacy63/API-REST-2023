package com.example.apirest.contraintes_hibernate_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import static com.example.apirest.configurations.ConfigurationConstantPool.HASH_LENGTH;
import static com.example.apirest.configurations.ConfigurationConstantPool.SALT_LENGTH;

public final class MinimumPasswordHashSizeValidator implements ConstraintValidator<MinimumPasswordHashSize, String> {
    @Override
    public void initialize(MinimumPasswordHashSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String hashPassword, ConstraintValidatorContext constraintValidatorContext) {
        // encodage en base64 : consiste à coder chaque groupe de 24 bits (3bytes) successifs de
        // données par une chaîne de 4 caractères
        int minimumLength = ((SALT_LENGTH / 3) + (HASH_LENGTH / 3)) * 4;

        int[] restes = {(SALT_LENGTH % 3), (HASH_LENGTH % 3)};
        for(int reste : restes){
            switch(reste){
                case 1:
                    // il reste un seul octet (8 bits) à coder, alors on ajoute à droite 4 bits à zéros pour former 2 caractères
                    minimumLength = minimumLength + 2;
                    break;
                case 2:
                    // il reste seulement 2 octets (16 bits) à coder, alors on ajoute à droite 2 bits à zéros pour former 3 caractères
                    minimumLength = minimumLength + 3;
                    break;
            }
        }

        return (hashPassword.length() >= minimumLength);

    }
}
