package com.example.apirest.dtos;

import java.io.Serializable;

public final class JwtResponseDto implements Serializable {

    private final String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}
