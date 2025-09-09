package com.vendorloginservice.exceptions;

public class TokenPersistenceException extends RuntimeException {
    public TokenPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}