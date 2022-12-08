package com.example.articlemongo.controller;

import com.example.articlemongo.exception.CustomError;
import com.example.articlemongo.exception.CustomNotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomNotFountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError notFound(CustomNotFountException e) {
        return new CustomError(404, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError internalServerError(Exception e) {
        return new CustomError(500, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError badRequest(IllegalArgumentException e) {
        return new CustomError(400, e.getMessage());
    }
}
