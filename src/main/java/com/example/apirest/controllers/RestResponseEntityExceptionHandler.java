package com.example.apirest.controllers;

import com.example.apirest.exceptions.AbstractExistingException;
import com.example.apirest.exceptions.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *  Class as an interceptor of exceptions thrown by methods annotated with @RequestMapping. No need try/catch
 *  block in methods controller anymore. Consolidate our multiple, scattered exception handlers into a single,
 *  global error handling component. Provides mapping of several exceptions to the same method, to be handled together
 */
@ControllerAdvice
public final class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger logger = LogManager.getLogger(UserController.class);

    @ExceptionHandler(value = { ValidatorException.class, AbstractExistingException.class})
    protected ResponseEntity<Object> handleValidatorException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { DisabledException.class })
    protected ResponseEntity<Object> handleServerException(DisabledException dex, WebRequest request) {
        logger.error(dex.getMessage());
        return handleExceptionInternal(dex, "USER_DISABLED",
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    protected ResponseEntity<Object> handleServerException(BadCredentialsException bcex, WebRequest request) {
        logger.error(bcex.getMessage());
        return handleExceptionInternal(bcex, "INVALID_CREDENTIALS",
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleServerException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        return handleExceptionInternal(ex, null,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}