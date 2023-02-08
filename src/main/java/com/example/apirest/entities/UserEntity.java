package com.example.apirest.entities;

import com.example.apirest.entities.interfaces.IUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public final class UserEntity extends AbstractEntity implements Serializable, IUser {

    @Column(name = "email")
    private String email;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "last_update")
    private LocalDate pwd_last_update;

    /* CONSTRUCTORS */
    public UserEntity() {
        super();
    }

    public UserEntity(IUser user) {
        super(user.getId());
        this.email = user.getEmail();
        this.pseudo = user.getPseudo();
        this.password = user.getPassword();
        this.pwd_last_update = user.getLast_update();
    }

    public UserEntity(String email, String pseudo, String password, LocalDate last_update) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.pwd_last_update = last_update;
    }

    /* GETTER */
    public String getEmail() { return email; }

    public String getPseudo() { return pseudo; }

    public String getPassword() { return password; }

    public LocalDate getLast_update() { return pwd_last_update; }

    /* SETTER */
    public void setPassword(String password) { this.password = password; }

    public void setLast_update(LocalDate last_update) { this.pwd_last_update = last_update; }

    @Override
    public String toString() {
        return "UserEntity{" +  "pseudo=" + pseudo + '}';
    }
}
