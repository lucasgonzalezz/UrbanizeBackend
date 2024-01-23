package com.ecommerce.urbanize.exception;

public class CannotPerformOperationException extends RuntimeException {

    public CannotPerformOperationException(String message) {
        super("ERROR: Cannot perform operation:" + message);
    }

}