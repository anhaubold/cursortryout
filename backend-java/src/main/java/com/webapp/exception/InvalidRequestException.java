package com.webapp.exception;

/**
 * Exception thrown when a request is invalid (e.g., invalid status value).
 * 
 * <p>This exception is used for 400 Bad Request responses.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * Constructs a new InvalidRequestException with the specified message.
     * 
     * @param message The detail message
     */
    public InvalidRequestException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidRequestException with the specified message and cause.
     * 
     * @param message The detail message
     * @param cause The cause of the exception
     */
    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}


