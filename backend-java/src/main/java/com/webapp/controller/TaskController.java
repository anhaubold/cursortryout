package com.webapp.controller;

import com.webapp.dto.TaskDto;
import com.webapp.dto.UpdateTaskStatusDto;
import com.webapp.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for task-related HTTP endpoints.
 * 
 * <p>Handles HTTP requests and delegates to TaskService.
 * Provides REST API endpoints for task management.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}", allowCredentials = "true")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    /**
     * Constructor with dependency injection.
     * 
     * @param taskService Task service
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET /api/tasks
     * Retrieves all tasks, optionally filtered by user ID.
     * Query parameter: ?userId=123
     * 
     * @param userId Optional user ID filter
     * @return List of tasks
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(required = false) Long userId) {
        logger.debug("GET /api/tasks - Retrieving tasks with userId filter: {}", userId);
        List<TaskDto> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/:id
     * Retrieves a task by ID.
     * 
     * @param id Task ID
     * @return Task DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        logger.debug("GET /api/tasks/{} - Retrieving task by ID", id);
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * POST /api/tasks
     * Creates a new task.
     * 
     * @param taskDto Task data
     * @return Created task DTO
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        logger.debug("POST /api/tasks - Creating task with title: {}", taskDto.getTitle());
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * PUT /api/tasks/:id
     * Updates an existing task.
     * 
     * @param id Task ID
     * @param taskDto Updated task data
     * @return Updated task DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) {
        logger.debug("PUT /api/tasks/{} - Updating task", id);
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * PATCH /api/tasks/:id/status
     * Updates task status.
     * 
     * @param id Task ID
     * @param statusDto Status update data
     * @return Updated task DTO
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDto> updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskStatusDto statusDto) {
        logger.debug("PATCH /api/tasks/{}/status - Updating task status to: {}", id, statusDto.getStatus());
        TaskDto updatedTask = taskService.updateTaskStatus(id, statusDto);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * DELETE /api/tasks/:id
     * Deletes a task by ID.
     * 
     * @param id Task ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.debug("DELETE /api/tasks/{} - Deleting task", id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}


