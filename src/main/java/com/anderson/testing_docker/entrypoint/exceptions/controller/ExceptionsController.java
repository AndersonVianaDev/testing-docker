package com.anderson.testing_docker.entrypoint.exceptions.controller;

import com.anderson.testing_docker.core.exceptions.DataConflictException;
import com.anderson.testing_docker.core.exceptions.NotFoundException;
import com.anderson.testing_docker.core.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static com.anderson.testing_docker.core.exceptions.constants.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler
    public ResponseEntity<StandardException> notFoundException(NotFoundException e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler
    public ResponseEntity<StandardException> dataConflictException(DataConflictException e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler
    public ResponseEntity<StandardException> genericException(Exception e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

}
