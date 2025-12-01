package com.webapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity representing a user in the system.
 * 
 * <p>Contains user information and relationships to tasks.
 * Maps to the 'users' table in the database.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User's email address (unique).
     * Must not be null and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User's full name.
     * Must not be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Timestamp when the user was created.
     * Automatically set on creation.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the user was last updated.
     * Automatically updated on modification.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Tasks associated with this user.
     * One-to-many relationship with cascade delete.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor with email and name.
     * 
     * @param email User's email address
     * @param name User's full name
     */
    public User(String email, String name) {
        this.email = email;
        this.name = name;
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

    /**
     * Gets the list of tasks associated with this user.
     * 
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks associated with this user.
     * 
     * @param tasks List of tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this user.
     * 
     * @param task Task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }

    /**
     * Removes a task from this user.
     * 
     * @param task Task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setUser(null);
    }
}


