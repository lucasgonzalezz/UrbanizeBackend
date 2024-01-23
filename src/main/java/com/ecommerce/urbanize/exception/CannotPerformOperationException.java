package com.ecommerce.urbanize.exception;

/**
 * Custom runtime exception indicating that a certain operation cannot be
 * performed.
 */
public class CannotPerformOperationException extends RuntimeException {

    /**
     * Constructs a new CannotPerformOperationException with the specified detail
     * message.
     * 
     * @param message The detail message indicating the operation that cannot be
     *                performed.
     */
    public CannotPerformOperationException(String message) {
        super("ERROR: Cannot perform operation: " + message);
    }

}