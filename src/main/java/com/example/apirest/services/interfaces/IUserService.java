package com.example.apirest.services.interfaces;

import com.example.apirest.entities.interfaces.IUser;
import com.example.apirest.exceptions.ExistingUserException;
import com.example.apirest.exceptions.ValidatorException;
import org.apache.commons.math3.exception.NullArgumentException;

import java.security.GeneralSecurityException;

public interface IUserService {

    void createNewUserAccount(IUser user) throws NullArgumentException, ValidatorException, ExistingUserException;
}
