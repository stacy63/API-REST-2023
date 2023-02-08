package com.example.apirest.services;

import com.example.apirest.contraintes_hibernate_validators.groups.HashProcessChecks;
import com.example.apirest.contraintes_hibernate_validators.groups.UserPasswordEntryChecks;
import com.example.apirest.dtos.UserDto;
import com.example.apirest.entities.UserEntity;
import com.example.apirest.entities.interfaces.IUser;
import com.example.apirest.exceptions.ExistingUserException;
import com.example.apirest.exceptions.ValidatorException;
import com.example.apirest.mappers.UserMapper;
import com.example.apirest.repository.UserRepository;
import com.example.apirest.services.interfaces.IUserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.apache.commons.math3.exception.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public final class UserService extends AbstractService implements IUserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void createNewUserAccount(IUser userDto) throws NullArgumentException, ValidatorException, ExistingUserException {
        if(userDto == null){
            throw new NullArgumentException();
        }
        /* On effectue la validation avec le groupe Default car dans ce cas, on sait que l'id est égal à zero et qu'aucun process de hash
         * n'a encore été appelé puisqu'il s'agit de la création d'un nouveau user en base. On vérifie cependant que le mdp entré respecte
         * les contraintes de sécurité via le groupe UserPasswordEntryChecks.*/
        Set<ConstraintViolation<IUser>> constraintViolations = validator.validate(userDto, UserPasswordEntryChecks.class, Default.class);
        if(constraintViolations.size() > 0){
            throw new ValidatorException("Utilisateur invalide");
        }
        // Comme un utilisateur est lié et identifié via un email, on peut rechercher un utilisateur via l'email qui doit ainsi être unique en base
        Collection<UserEntity> user = userRepository.findByEmailOrPseudo(userDto.getEmail(), userDto.getPseudo());
        if(!user.isEmpty()){
            throw new ExistingUserException("Identifiants déjà utilisés");
        }
        // L'utilisateur n'existant pas on peut le créer

        /* Hash du mot de passe + salt, le sel est directement généré à l'appel de la méthode. This encryption algorithm will generate the salt for us internally.
         * The algorithm stores the salt within the output hash for later use in validating a password. */
        String outputHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(outputHash);
        userDto.setLast_update(LocalDate.now());

        /* Comme nous avons déjà vérifié les champs appartenant au groupe par défaut de validation, on ne va vérifier ici seulement
         * le HashProcessChecks pour vérifier les éléments de sécurité qui ont été générés après coups */
        constraintViolations = validator.validate(userDto, HashProcessChecks.class);
        if(constraintViolations.size() > 0){
            throw new ValidatorException("Utilisateur prêt à être sauvé en base invalide");
        }

        userRepository.save(new UserEntity(userDto));
    }

    @Override
    public UserDto loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(userEmail);
        if(user.isPresent()){
            return userMapper.userToUserDto(user.get());
        }
        throw new UsernameNotFoundException("User " + userEmail + " not found");
    }
}
