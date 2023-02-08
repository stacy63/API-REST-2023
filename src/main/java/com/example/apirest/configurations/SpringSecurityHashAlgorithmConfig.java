package com.example.apirest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.apirest.configurations.ConfigurationConstantPool.*;

/**
 * La création du bean PasswordEncoder est indépendant de tout autre bean, on le sépare donc de tout injection de UserService qui doit
 * se voir injecter le bean PasswordEncoder. De la sorte, Spring n'aura aucun problème à pouvoir générer le PasswordEncoder bean avant
 * la génération du UserService bean et il n'y aura pas de problème de dépendances circulaires.
 */
@Configuration
public class SpringSecurityHashAlgorithmConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new Argon2PasswordEncoder(SALT_LENGTH, HASH_LENGTH, ARGON2_PARALLELISM, ARGON2_MEMORY, ARGON2_ITERATIONS);
    }
}
