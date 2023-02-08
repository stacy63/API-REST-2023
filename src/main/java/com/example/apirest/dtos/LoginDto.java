package com.example.apirest.dtos;

import java.io.Serializable;

public final class LoginDto implements Serializable {

    private String userEmail;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
