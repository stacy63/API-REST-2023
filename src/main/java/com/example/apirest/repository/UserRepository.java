package com.example.apirest.repository;

import com.example.apirest.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT user FROM UserEntity user WHERE lower(user.email) = lower(:userEmail) OR lower(user.pseudo) = lower(:userPseudo)")
    Collection<UserEntity> findByEmailOrPseudo(@Param("userEmail") String email, @Param("userPseudo") String pseudo);

    @Query("SELECT user FROM UserEntity user WHERE lower(user.email) = lower(:userEmail)")
    Optional<UserEntity> findByEmail(@Param("userEmail") String email);
}
