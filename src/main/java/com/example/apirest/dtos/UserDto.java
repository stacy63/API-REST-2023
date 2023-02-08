package com.example.apirest.dtos;

import com.example.apirest.contraintes_hibernate_validators.MinimumPasswordHashSize;
import com.example.apirest.contraintes_hibernate_validators.MustRespectEmailBasicStructure;
import com.example.apirest.contraintes_hibernate_validators.groups.AlreadyExistingEntityIdChecks;
import com.example.apirest.contraintes_hibernate_validators.groups.HashProcessChecks;
import com.example.apirest.contraintes_hibernate_validators.groups.UserPasswordEntryChecks;
import com.example.apirest.entities.interfaces.IUser;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

import static com.example.apirest.classes.ConstantPool.*;

/**
 * On retrouve quatre groupes de validations selon le cas d'utilisation:
 *  - UserPasswordEntryChecks: cas où l'on vérifie les contraintes de validité de mot de passe entré par l'utilisateur
 *  - HashProcessChecks: cas où l'on procède à l'enregistrement d'un user en base après avoir appliqué le hash process,
 *      qu'il s'agisse de la première fois ou d'un update de mdp
 *  - AlreadyExistingEntityIdChecks: cas de check de l'id supérieur à 1 pour un user déjà existant en bdd
 *  - Default: cas de champs devant être toujours validés peu importe le cas. Dans le cas d'une nouvelle entité il sera
 *      accompagné du UserPasswordEntryChecks, sinon il sera accompagné des deux autres validations.
 */
public final class UserDto implements IUser, UserDetails {

    @Positive(message = "UserDto.id must be greater than 1", groups = AlreadyExistingEntityIdChecks.class)
    private int id;

    // @Email ne vérifie pas avoir la structure basique : @ puis le . d'où @MustRespectEmailBasicStructure
    @NotNull(message = "UserDto.email may not be null")
    @Email(message = "UserDto.email is not in the expected format")
    @MustRespectEmailBasicStructure
    private String email;

    @NotBlank(message = "UserDto.pseudo may not be blank or null")
    @Pattern(regexp = REGEX_CARAC_SPECIAUX_PSEUDO, message = "UserDto.pseudo is not in the expected format")
    private String pseudo;

    /* After hash process it isn't possible to have an exact output length because it depends on the length chosen for various
     * parameters such as the salt and the output hash itself. Afin de permettre la maintenabilité des processs de hash, aucune vérification
     * particulière à un algorithme ou à un paramètre de configuration n'est mis en place lors de la validation, excepté un minimum de taille
     * calculé en fonction des constantes de config définie. Ce champ contient une unique chaîne après avoir été hash contenant le sel et le
     * mdp => tout en un. */
    @NotBlank(message = "UserDto.password may not be blank or null")
    @Length(min = 12, message = "UserDto.password is not long enough", groups = UserPasswordEntryChecks.class)
    @Pattern(regexp = REGEX_PASSWORD_MINIMUM_CRITERIA, message = "UserDto.password is not in the expected format", groups = UserPasswordEntryChecks.class)
    @MinimumPasswordHashSize(groups = HashProcessChecks.class)
    private String password;

    // S'il s'agit d'une création de compte il n'y aura pas d'ancien update de mot de passe
    @NotNull(message = "UserDto.pwd_last_update may not be null", groups = HashProcessChecks.class)
    @PastOrPresent(message = "UserDto.pwd_last_update cannot be subsequent to the date of the day", groups = HashProcessChecks.class)
    private LocalDate pwd_last_update;

    /* CONSTRUCTORS */
    public UserDto(){
    }

    public UserDto(String email, String pseudo, String password) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
    }

    public UserDto(int id, String email, String pseudo, String password, LocalDate last_update) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.pwd_last_update = last_update;
    }

    /*GETTER*/
    @Override
    public int getId() { return id; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String getPseudo() { return pseudo; }

    @Override
    public LocalDate getLast_update() { return pwd_last_update; }

    /*SETTER*/
    // Tous les setters sont requis pour le mapper
    public void setId(int id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    @Override
    public void setPassword(String password) { this.password = password; }

    @Override
    public void setLast_update(LocalDate last_update) { this.pwd_last_update = last_update; }

    @Override
    public String toString(){
        return "UserDto{" + "id=" + id + ", pseudo= " + pseudo + '}';
    }

    /* UserDetails Implementation*/
    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
