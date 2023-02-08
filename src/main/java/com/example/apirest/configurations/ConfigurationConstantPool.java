package com.example.apirest.configurations;

public abstract class ConfigurationConstantPool {

    public static final int SALT_LENGTH = 16;
    public static final int HASH_LENGTH = 32;
    public static final int ARGON2_PARALLELISM = 1;
    public static final int ARGON2_MEMORY = 38798;
    public static final int ARGON2_ITERATIONS = 1;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String AUTHORIZATION_HEADER = "Authorization";
}
