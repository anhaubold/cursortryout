package com.webapp.dto;

import com.webapp.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for Task entity.
 * 
 * <p>Used for request/response operations to decouple API layer from entity layer.
 * Contains validation annotations for input validation.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class TaskDto {

    private Long id;

    @NotBlank(message = "Task title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private TaskStatus status;

    @NotNull(message = "User ID is required")
    private Long userId;

    /**
     * Default constructor.
     */
    public TaskDto() {
    }

    /**
     * Constructor with title and user ID.
     * 
     * @param title Task title
     * @param userId User ID who owns the task
     */
    public TaskDto(String title, Long userId) {
        this.title = title;
        this.userId = userId;
    }

    /**
     * Gets the task ID.
     * 
     * @return Task ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the task ID.
     * 
     * @param id Task ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the task title.
     * 
     * @return Task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title.
     * 
     * @param title Task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the task description.
     * 
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task description.
     * 
     * @param description Task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the task status.
     * 
     * @return Task status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     * 
     * @param status Task status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Gets the user ID who owns this task.
     * 
     * @return User ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID who owns this task.
     * 
     * @param userId User ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


