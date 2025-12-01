package com.webapp.repository;

import com.webapp.entity.Task;
import com.webapp.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Task entity operations.
 * 
 * <p>Provides data access methods for task management.
 * Extends Spring Data JPA's JpaRepository for standard CRUD operations.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Finds all tasks for a specific user.
     * 
     * @param userId User ID to filter by
     * @return List of tasks for the user, ordered by creation date descending
     */
    @Query("SELECT t FROM Task t WHERE t.userId = :userId ORDER BY t.createdAt DESC")
    List<Task> findByUserId(@Param("userId") Long userId);

    /**
     * Finds a task by ID with the associated user loaded.
     * 
     * @param id Task ID
     * @return Task with user if found, null otherwise
     */
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.user WHERE t.id = :id")
    Task findByIdWithUser(@Param("id") Long id);

    /**
     * Finds all tasks with a specific status.
     * 
     * @param status Task status to filter by
     * @return List of tasks with the specified status
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Finds all tasks for a specific user with a specific status.
     * 
     * @param userId User ID to filter by
     * @param status Task status to filter by
     * @return List of tasks matching both criteria
     */
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
}


