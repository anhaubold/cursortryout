import { Repository } from 'typeorm';
import { AppDataSource } from '../config/data-source';
import { Task, TaskStatus } from '../entities/Task';
import { ApiError } from '../middleware/error-handler';

/**
 * Repository for Task entity operations.
 * Provides data access methods for task management.
 */
export class TaskRepository {
  private repository: Repository<Task>;

  constructor() {
    this.repository = AppDataSource.getRepository(Task);
  }

  /**
   * Finds all tasks, optionally filtered by user ID.
   * @param {number} userId - Optional user ID filter
   * @returns {Promise<Task[]>} Array of tasks
   */
  async findAll(userId?: number): Promise<Task[]> {
    const where = userId ? { userId } : {};
    return await this.repository.find({
      where,
      relations: ['user'],
      order: { createdAt: 'DESC' }
    });
  }

  /**
   * Finds a task by ID.
   * @param {number} id - Task ID
   * @returns {Promise<Task>} Task entity
   * @throws {ApiError} If task not found
   */
  async findById(id: number): Promise<Task> {
    const task = await this.repository.findOne({
      where: { id },
      relations: ['user']
    });

    if (!task) {
      throw new ApiError(404, `Task with ID ${id} not found`);
    }

    return task;
  }

  /**
   * Creates a new task.
   * @param {Partial<Task>} taskData - Task data
   * @returns {Promise<Task>} Created task entity
   */
  async create(taskData: Partial<Task>): Promise<Task> {
    const task = this.repository.create(taskData);
    return await this.repository.save(task);
  }

  /**
   * Updates an existing task.
   * @param {number} id - Task ID
   * @param {Partial<Task>} taskData - Updated task data
   * @returns {Promise<Task>} Updated task entity
   * @throws {ApiError} If task not found
   */
  async update(id: number, taskData: Partial<Task>): Promise<Task> {
    const task = await this.findById(id);
    Object.assign(task, taskData);
    return await this.repository.save(task);
  }

  /**
   * Updates task status.
   * @param {number} id - Task ID
   * @param {TaskStatus} status - New status
   * @returns {Promise<Task>} Updated task entity
   * @throws {ApiError} If task not found
   */
  async updateStatus(id: number, status: TaskStatus): Promise<Task> {
    return await this.update(id, { status });
  }

  /**
   * Deletes a task by ID.
   * @param {number} id - Task ID
   * @throws {ApiError} If task not found
   */
  async delete(id: number): Promise<void> {
    const task = await this.findById(id);
    await this.repository.remove(task);
  }
}



