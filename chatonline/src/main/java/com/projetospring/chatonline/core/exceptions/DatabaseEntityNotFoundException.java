package com.projetospring.chatonline.core.exceptions;

public class DatabaseEntityNotFoundException extends RuntimeException {
    public DatabaseEntityNotFoundException(String message) {
        super(message);
    }
}
