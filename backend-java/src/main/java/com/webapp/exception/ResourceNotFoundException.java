package com.webapp.exception;

/**
 * Exception thrown when a requested resource is not found.
 * 
 * <p>This exception is used for 404 Not Found responses.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified message.
     * 
     * @param message The detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified message and cause.
     * 
     * @param message The detail message
     * @param cause The cause of the exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


