package com.example.articlemongo.exception;

public class CustomNotFountException extends RuntimeException{

    public CustomNotFountException() {
    }

    public CustomNotFountException(String message) {
        super(message);
    }

    public CustomNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomNotFountException(Throwable cause) {
        super(cause);
    }
}
