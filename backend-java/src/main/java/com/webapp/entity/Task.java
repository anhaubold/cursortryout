package com.webapp.entity;

import com.webapp.enums.TaskStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Task entity representing a task in the system.
 * 
 * <p>Contains task information and relationship to user.
 * Maps to the 'tasks' table in the database.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Entity
@Table(name = "tasks")
public class Task {

    /**
     * Unique identifier for the task.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the task.
     * Must not be null.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Detailed description of the task.
     * Optional field.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Current status of the task.
     * Defaults to PENDING.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    /**
     * ID of the user who owns this task.
     * Foreign key to users table.
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * User who owns this task.
     * Many-to-one relationship with cascade delete.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * Timestamp when the task was created.
     * Automatically set on creation.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the task was last updated.
     * Automatically updated on modification.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public Task() {
    }

    /**
     * Constructor with title and user ID.
     * 
     * @param title Task title
     * @param userId User ID who owns the task
     */
    public Task(String title, Long userId) {
        this.title = title;
        this.userId = userId;
    }

    /**
     * Pre-persist callback to set creation and update timestamps.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Pre-update callback to update the modification timestamp.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

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

    /**
     * Gets the user who owns this task.
     * 
     * @return User entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who owns this task.
     * 
     * @param user User entity
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the creation timestamp.
     * 
     * @return Creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * 
     * @param createdAt Creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp.
     * 
     * @return Last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp.
     * 
     * @param updatedAt Last update timestamp
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

