package com.webapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for User entity.
 * 
 * <p>Used for request/response operations to decouple API layer from entity layer.
 * Contains validation annotations for input validation.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class UserDto {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    /**
     * Default constructor.
     */
    public UserDto() {
    }

    /**
     * Constructor with email and name.
     * 
     * @param email User's email address
     * @param name User's full name
     */
    public UserDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    /**
     * Gets the user ID.
     * 
     * @return User ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * 
     * @param id User ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's email address.
     * 
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * 
     * @param email Email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's full name.
     * 
     * @return Full name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     * 
     * @param name Full name
     */
    public void setName(String name) {
        this.name = name;
    }
}


