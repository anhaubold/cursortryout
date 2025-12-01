import { TaskRepository } from '../repositories/TaskRepository';
import { Task, TaskStatus } from '../entities/Task';
import { ApiError } from '../middleware/error-handler';

/**
 * Service for task business logic.
 * Handles task-related operations and validation.
 */
export class TaskService {
  private taskRepository: TaskRepository;

  constructor() {
    this.taskRepository = new TaskRepository();
  }

  /**
   * Retrieves all tasks, optionally filtered by user ID.
   * @param {number} userId - Optional user ID filter
   * @returns {Promise<Task[]>} Array of tasks
   */
  async getAllTasks(userId?: number): Promise<Task[]> {
    return await this.taskRepository.findAll(userId);
  }

  /**
   * Retrieves a task by ID.
   * @param {number} id - Task ID
   * @returns {Promise<Task>} Task entity
   * @throws {ApiError} If task not found
   */
  async getTaskById(id: number): Promise<Task> {
    return await this.taskRepository.findById(id);
  }

  /**
   * Creates a new task with validation.
   * @param {Partial<Task>} taskData - Task data
   * @returns {Promise<Task>} Created task entity
   * @throws {ApiError} If validation fails
   */
  async createTask(taskData: Partial<Task>): Promise<Task> {
    this.validateTaskData(taskData);
    return await this.taskRepository.create(taskData);
  }

  /**
   * Updates an existing task.
   * @param {number} id - Task ID
   * @param {Partial<Task>} taskData - Updated task data
   * @returns {Promise<Task>} Updated task entity
   * @throws {ApiError} If task not found or validation fails
   */
  async updateTask(id: number, taskData: Partial<Task>): Promise<Task> {
    if (taskData.status) {
      this.validateStatus(taskData.status);
    }
    return await this.taskRepository.update(id, taskData);
  }

  /**
   * Updates task status.
   * @param {number} id - Task ID
   * @param {TaskStatus} status - New status
   * @returns {Promise<Task>} Updated task entity
   * @throws {ApiError} If task not found or status is invalid
   */
  async updateTaskStatus(id: number, status: TaskStatus): Promise<Task> {
    this.validateStatus(status);
    return await this.taskRepository.updateStatus(id, status);
  }

  /**
   * Deletes a task by ID.
   * @param {number} id - Task ID
   * @throws {ApiError} If task not found
   */
  async deleteTask(id: number): Promise<void> {
    await this.taskRepository.delete(id);
  }

  /**
   * Validates task data.
   * @param {Partial<Task>} taskData - Task data to validate
   * @throws {ApiError} If validation fails
   */
  private validateTaskData(taskData: Partial<Task>): void {
    if (!taskData.title) {
      throw new ApiError(400, 'Task title is required');
    }
    if (!taskData.userId) {
      throw new ApiError(400, 'User ID is required');
    }
    if (taskData.status) {
      this.validateStatus(taskData.status);
    }
  }

  /**
   * Validates task status.
   * @param {string} status - Status to validate
   * @throws {ApiError} If status is invalid
   */
  private validateStatus(status: string): void {
    if (!Object.values(TaskStatus).includes(status as TaskStatus)) {
      throw new ApiError(400, `Invalid task status. Must be one of: ${Object.values(TaskStatus).join(', ')}`);
    }
  }
}



