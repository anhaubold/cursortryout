package com.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class.
 * 
 * <p>This is the entry point for the Spring Boot backend application.
 * It provides REST API endpoints for user and task management.</p>
 * 
 * <p>The application uses:
 * <ul>
 *   <li>Spring Boot Web for REST endpoints</li>
 *   <li>Spring Data JPA for database operations</li>
 *   <li>SQLite database (same as TypeScript backend)</li>
 * </ul>
 * </p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@SpringBootApplication
public class BackendApplication {

    /**
     * Main method to start the Spring Boot application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}


