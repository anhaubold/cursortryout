package com.webapp.exception;

/**
 * Exception thrown when a resource conflict occurs (e.g., duplicate email).
 * 
 * <p>This exception is used for 409 Conflict responses.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class ResourceConflictException extends RuntimeException {

    /**
     * Constructs a new ResourceConflictException with the specified message.
     * 
     * @param message The detail message
     */
    public ResourceConflictException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceConflictException with the specified message and cause.
     * 
     * @param message The detail message
     * @param cause The cause of the exception
     */
    public ResourceConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}


