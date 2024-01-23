package com.ecommerce.urbanize.bean;

import java.util.Date;

/**
 * Bean class representing an error response.
 */
public class ErrorResponseBean {

    private Date timestamp;
    private String status;
    private String message;
    private String details;

    /**
     * Constructor to initialize the ErrorResponseBean.
     * 
     * @param timestamp The timestamp when the error occurred.
     * @param status    The HTTP status code of the error.
     * @param message   A descriptive message of the error.
     * @param details   Additional details about the error.
     */
    public ErrorResponseBean(Date timestamp, String status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    /**
     * Get the timestamp of the error.
     * 
     * @return The timestamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Set the timestamp of the error.
     * 
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get the HTTP status code of the error.
     * 
     * @return The status code.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the HTTP status code of the error.
     * 
     * @param status The status code to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the descriptive message of the error.
     * 
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the descriptive message of the error.
     * 
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get additional details about the error.
     * 
     * @return The error details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set additional details about the error.
     * 
     * @param details The error details to set.
     */
    public void setDetails(String details) {
        this.details = details;
    }
}