package com.webapp.service;

import com.webapp.dto.TaskDto;
import com.webapp.dto.UpdateTaskStatusDto;
import com.webapp.entity.Task;
import com.webapp.enums.TaskStatus;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.ResourceNotFoundException;
import com.webapp.repository.TaskRepository;
import com.webapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for task business logic.
 * 
 * <p>Handles task-related operations and validation.
 * Provides a layer between controllers and repositories.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Service
@Transactional
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    /**
     * Constructor with dependency injection.
     * 
     * @param taskRepository Task repository
     * @param userRepository User repository
     */
    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all tasks, optionally filtered by user ID.
     * 
     * @param userId Optional user ID filter
     * @return List of tasks as DTOs
     */
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks(Long userId) {
        logger.debug("Retrieving tasks with userId filter: {}", userId);
        List<Task> tasks;
        if (userId != null) {
            tasks = taskRepository.findByUserId(userId);
        } else {
            tasks = taskRepository.findAll();
        }
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a task by ID.
     * 
     * @param id Task ID
     * @return Task DTO
     * @throws ResourceNotFoundException If task not found
     */
    @Transactional(readOnly = true)
    public TaskDto getTaskById(Long id) {
        logger.debug("Retrieving task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
        return convertToDto(task);
    }

    /**
     * Creates a new task with validation.
     * 
     * @param taskDto Task data
     * @return Created task DTO
     * @throws ResourceNotFoundException If user not found
     * @throws InvalidRequestException If validation fails
     */
    public TaskDto createTask(TaskDto taskDto) {
        logger.debug("Creating task with title: {}", taskDto.getTitle());
        validateTaskData(taskDto);
        
        // Verify user exists
        userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + taskDto.getUserId() + " not found"));
        
        Task task = convertToEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        logger.info("Created task with ID: {}", savedTask.getId());
        return convertToDto(savedTask);
    }

    /**
     * Updates an existing task.
     * 
     * @param id Task ID
     * @param taskDto Updated task data
     * @return Updated task DTO
     * @throws ResourceNotFoundException If task or user not found
     * @throws InvalidRequestException If validation fails
     */
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        logger.debug("Updating task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
        
        // If userId is being updated, verify the new user exists
        if (taskDto.getUserId() != null && !taskDto.getUserId().equals(task.getUserId())) {
            userRepository.findById(taskDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with ID " + taskDto.getUserId() + " not found"));
        }
        
        if (taskDto.getStatus() != null) {
            validateStatus(taskDto.getStatus());
        }
        
        if (taskDto.getTitle() != null) {
            task.setTitle(taskDto.getTitle());
        }
        if (taskDto.getDescription() != null) {
            task.setDescription(taskDto.getDescription());
        }
        if (taskDto.getStatus() != null) {
            task.setStatus(taskDto.getStatus());
        }
        if (taskDto.getUserId() != null) {
            task.setUserId(taskDto.getUserId());
        }
        
        Task updatedTask = taskRepository.save(task);
        logger.info("Updated task with ID: {}", updatedTask.getId());
        return convertToDto(updatedTask);
    }

    /**
     * Updates task status.
     * 
     * @param id Task ID
     * @param statusDto Status update data
     * @return Updated task DTO
     * @throws ResourceNotFoundException If task not found
     * @throws InvalidRequestException If status is invalid
     */
    public TaskDto updateTaskStatus(Long id, UpdateTaskStatusDto statusDto) {
        logger.debug("Updating task status for ID: {} to status: {}", id, statusDto.getStatus());
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
        
        validateStatus(statusDto.getStatus());
        task.setStatus(statusDto.getStatus());
        
        Task updatedTask = taskRepository.save(task);
        logger.info("Updated task status for ID: {} to status: {}", updatedTask.getId(), updatedTask.getStatus());
        return convertToDto(updatedTask);
    }

    /**
     * Deletes a task by ID.
     * 
     * @param id Task ID
     * @throws ResourceNotFoundException If task not found
     */
    public void deleteTask(Long id) {
        logger.debug("Deleting task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
        taskRepository.delete(task);
        logger.info("Deleted task with ID: {}", id);
    }

    /**
     * Validates task data.
     * 
     * @param taskDto Task data to validate
     * @throws InvalidRequestException If validation fails
     */
    private void validateTaskData(TaskDto taskDto) {
        if (taskDto.getTitle() == null || taskDto.getTitle().trim().isEmpty()) {
            throw new InvalidRequestException("Task title is required");
        }
        if (taskDto.getUserId() == null) {
            throw new InvalidRequestException("User ID is required");
        }
        if (taskDto.getStatus() != null) {
            validateStatus(taskDto.getStatus());
        }
    }

    /**
     * Validates task status.
     * 
     * @param status Status to validate
     * @throws InvalidRequestException If status is invalid
     */
    private void validateStatus(TaskStatus status) {
        if (status == null) {
            throw new InvalidRequestException("Status is required");
        }
        // Enum validation is handled by Java, but we can add additional checks if needed
    }

    /**
     * Converts Task entity to TaskDto.
     * 
     * @param task Task entity
     * @return TaskDto
     */
    private TaskDto convertToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setUserId(task.getUserId());
        return dto;
    }

    /**
     * Converts TaskDto to Task entity.
     * 
     * @param taskDto TaskDto
     * @return Task entity
     */
    private Task convertToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus() != null ? taskDto.getStatus() : TaskStatus.PENDING);
        task.setUserId(taskDto.getUserId());
        return task;
    }
}

