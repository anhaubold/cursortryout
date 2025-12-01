package com.webapp.controller;

import com.webapp.dto.UserDto;
import com.webapp.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user-related HTTP endpoints.
 * 
 * <p>Handles HTTP requests and delegates to UserService.
 * Provides REST API endpoints for user management.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}", allowCredentials = "true")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructor with dependency injection.
     * 
     * @param userService User service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/users
     * Retrieves all users.
     * 
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.debug("GET /api/users - Retrieving all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * GET /api/users/:id
     * Retrieves a user by ID.
     * 
     * @param id User ID
     * @return User DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        logger.debug("GET /api/users/{} - Retrieving user by ID", id);
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * POST /api/users
     * Creates a new user.
     * 
     * @param userDto User data
     * @return Created user DTO
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.debug("POST /api/users - Creating user with email: {}", userDto.getEmail());
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * PUT /api/users/:id
     * Updates an existing user.
     * 
     * @param id User ID
     * @param userDto Updated user data
     * @return Updated user DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        logger.debug("PUT /api/users/{} - Updating user", id);
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * DELETE /api/users/:id
     * Deletes a user by ID.
     * 
     * @param id User ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.debug("DELETE /api/users/{} - Deleting user", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


