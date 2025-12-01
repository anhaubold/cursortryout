package com.webapp.service;

import com.webapp.dto.UserDto;
import com.webapp.entity.User;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.ResourceConflictException;
import com.webapp.exception.ResourceNotFoundException;
import com.webapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for user business logic.
 * 
 * <p>Handles user-related operations and validation.
 * Provides a layer between controllers and repositories.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    /**
     * Constructor with dependency injection.
     * 
     * @param userRepository User repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users.
     * 
     * @return List of all users as DTOs
     */
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        logger.debug("Retrieving all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by ID.
     * 
     * @param id User ID
     * @return User DTO
     * @throws ResourceNotFoundException If user not found
     */
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        logger.debug("Retrieving user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return convertToDto(user);
    }

    /**
     * Creates a new user with validation.
     * 
     * @param userDto User data
     * @return Created user DTO
     * @throws ResourceConflictException If email already exists
     * @throws InvalidRequestException If validation fails
     */
    public UserDto createUser(UserDto userDto) {
        logger.debug("Creating user with email: {}", userDto.getEmail());
        validateUserData(userDto);
        
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResourceConflictException("User with email " + userDto.getEmail() + " already exists");
        }
        
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        logger.info("Created user with ID: {}", savedUser.getId());
        return convertToDto(savedUser);
    }

    /**
     * Updates an existing user.
     * 
     * @param id User ID
     * @param userDto Updated user data
     * @return Updated user DTO
     * @throws ResourceNotFoundException If user not found
     * @throws ResourceConflictException If email already exists (for different user)
     * @throws InvalidRequestException If validation fails
     */
    public UserDto updateUser(Long id, UserDto userDto) {
        logger.debug("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        
        if (userDto.getEmail() != null) {
            validateEmail(userDto.getEmail());
            // Check if email is already taken by another user
            userRepository.findByEmail(userDto.getEmail())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getId().equals(id)) {
                            throw new ResourceConflictException("User with email " + userDto.getEmail() + " already exists");
                        }
                    });
        }
        
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        
        User updatedUser = userRepository.save(user);
        logger.info("Updated user with ID: {}", updatedUser.getId());
        return convertToDto(updatedUser);
    }

    /**
     * Deletes a user by ID.
     * 
     * @param id User ID
     * @throws ResourceNotFoundException If user not found
     */
    public void deleteUser(Long id) {
        logger.debug("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        userRepository.delete(user);
        logger.info("Deleted user with ID: {}", id);
    }

    /**
     * Validates user data.
     * 
     * @param userDto User data to validate
     * @throws InvalidRequestException If validation fails
     */
    private void validateUserData(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new InvalidRequestException("Email is required");
        }
        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            throw new InvalidRequestException("Name is required");
        }
        validateEmail(userDto.getEmail());
    }

    /**
     * Validates email format.
     * 
     * @param email Email to validate
     * @throws InvalidRequestException If email format is invalid
     */
    private void validateEmail(String email) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidRequestException("Invalid email format");
        }
    }

    /**
     * Converts User entity to UserDto.
     * 
     * @param user User entity
     * @return UserDto
     */
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    /**
     * Converts UserDto to User entity.
     * 
     * @param userDto UserDto
     * @return User entity
     */
    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        return user;
    }
}


