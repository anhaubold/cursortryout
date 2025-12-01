package com.webapp.exception;

import java.time.LocalDateTime;

/**
 * Standard error response structure.
 * 
 * <p>Used by GlobalExceptionHandler to return consistent error responses.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class ErrorResponse {

    private String message;
    private Object details;
    private LocalDateTime timestamp;

    /**
     * Default constructor.
     */
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor with message.
     * 
     * @param message Error message
     */
    public ErrorResponse(String message) {
        this();
        this.message = message;
    }

    /**
     * Constructor with message and details.
     * 
     * @param message Error message
     * @param details Additional error details
     */
    public ErrorResponse(String message, Object details) {
        this(message);
        this.details = details;
    }

    /**
     * Gets the error message.
     * 
     * @return Error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     * 
     * @param message Error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets additional error details.
     * 
     * @return Error details
     */
    public Object getDetails() {
        return details;
    }

    /**
     * Sets additional error details.
     * 
     * @param details Error details
     */
    public void setDetails(Object details) {
        this.details = details;
    }

    /**
     * Gets the timestamp when the error occurred.
     * 
     * @return Timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the error occurred.
     * 
     * @param timestamp Timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


