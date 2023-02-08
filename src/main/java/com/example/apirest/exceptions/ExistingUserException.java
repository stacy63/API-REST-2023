package com.example.apirest.exceptions;

public final class ExistingUserException extends AbstractExistingException {

    public ExistingUserException(String message) {
        super(message);
    }
}
