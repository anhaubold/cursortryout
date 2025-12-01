package com.webapp.dto;

import com.webapp.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for updating task status.
 * 
 * <p>Used specifically for the PATCH /api/tasks/:id/status endpoint.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
public class UpdateTaskStatusDto {

    @NotNull(message = "Status is required")
    private TaskStatus status;

    /**
     * Default constructor.
     */
    public UpdateTaskStatusDto() {
    }

    /**
     * Constructor with status.
     * 
     * @param status Task status
     */
    public UpdateTaskStatusDto(TaskStatus status) {
        this.status = status;
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
}


