package com.ecommerce.urbanize.exception;

/**
 * Custom runtime exception indicating that a requested resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message The detail message indicating the resource that is not found.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}