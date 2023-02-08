package com.example.apirest.entities.interfaces;

import java.time.LocalDate;

public interface IUser {

    int getId();
    String getEmail();
    String getPseudo();
    String getPassword();
    LocalDate getLast_update();

    void setPassword(String password);
    void setLast_update(LocalDate last_update);
}
