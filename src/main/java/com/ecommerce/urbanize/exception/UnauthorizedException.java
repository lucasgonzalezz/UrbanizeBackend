package com.ecommerce.urbanize.exception;

/**
 * Custom runtime exception indicating an unauthorized access attempt.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructs a new UnauthorizedException with the specified detail message.
     * 
     * @param msg The detail message indicating the unauthorized access attempt.
     */
    public UnauthorizedException(String msg) {
        super("ERROR: Unauthorized access attempt: " + msg);
    }

}