package com.ecommerce.urbanize.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.ecommerce.urbanize.bean.ErrorResponseBean;

/**
 * Global exception handler for handling various types of exceptions in the
 * application.
 */
public class AppExceptionHandler {

    /**
     * Handles UnauthorizedException and returns a ResponseEntity with appropriate
     * error details.
     * 
     * @param ex      The UnauthorizedException that occurred.
     * @param request The current web request.
     * @return ResponseEntity with error details and HTTP status code UNAUTHORIZED.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.UNAUTHORIZED.name(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles ResourceNotFoundException and returns a ResponseEntity with
     * appropriate error details.
     * 
     * @param ex      The ResourceNotFoundException that occurred.
     * @param request The current web request.
     * @return ResponseEntity with error details and HTTP status code NOT_FOUND.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.NOT_FOUND.name(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic Exception and returns a ResponseEntity with appropriate error
     * details.
     * 
     * @param ex      The generic Exception that occurred.
     * @param request The current web request.
     * @return ResponseEntity with error details and HTTP status code
     *         INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAppException(Exception ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}