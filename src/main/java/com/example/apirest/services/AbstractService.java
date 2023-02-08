package com.example.apirest.services;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public abstract class AbstractService {

    protected Validator validator;

    public AbstractService() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
