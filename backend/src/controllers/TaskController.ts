import { Request, Response, NextFunction } from 'express';
import { TaskService } from '../services/TaskService';
import { ApiError } from '../middleware/error-handler';

/**
 * Controller for task-related HTTP endpoints.
 * Handles HTTP requests and delegates to TaskService.
 */
export class TaskController {
  private taskService: TaskService;

  constructor() {
    this.taskService = new TaskService();
  }

  /**
   * GET /api/tasks
   * Retrieves all tasks, optionally filtered by user ID.
   * Query parameter: ?userId=123
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  getAllTasks = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const userId = req.query.userId ? parseInt(req.query.userId as string, 10) : undefined;
      if (req.query.userId && isNaN(userId!)) {
        throw new ApiError(400, 'Invalid user ID in query parameter');
      }
      const tasks = await this.taskService.getAllTasks(userId);
      res.json(tasks);
    } catch (error) {
      next(error);
    }
  };

  /**
   * GET /api/tasks/:id
   * Retrieves a task by ID.
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  getTaskById = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const id = parseInt(req.params.id, 10);
      if (isNaN(id)) {
        throw new ApiError(400, 'Invalid task ID');
      }
      const task = await this.taskService.getTaskById(id);
      res.json(task);
    } catch (error) {
      next(error);
    }
  };

  /**
   * POST /api/tasks
   * Creates a new task.
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  createTask = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const task = await this.taskService.createTask(req.body);
      res.status(201).json(task);
    } catch (error) {
      next(error);
    }
  };

  /**
   * PUT /api/tasks/:id
   * Updates an existing task.
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  updateTask = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const id = parseInt(req.params.id, 10);
      if (isNaN(id)) {
        throw new ApiError(400, 'Invalid task ID');
      }
      const task = await this.taskService.updateTask(id, req.body);
      res.json(task);
    } catch (error) {
      next(error);
    }
  };

  /**
   * PATCH /api/tasks/:id/status
   * Updates task status.
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  updateTaskStatus = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const id = parseInt(req.params.id, 10);
      if (isNaN(id)) {
        throw new ApiError(400, 'Invalid task ID');
      }
      const { status } = req.body;
      if (!status) {
        throw new ApiError(400, 'Status is required');
      }
      const task = await this.taskService.updateTaskStatus(id, status);
      res.json(task);
    } catch (error) {
      next(error);
    }
  };

  /**
   * DELETE /api/tasks/:id
   * Deletes a task by ID.
   * 
   * @param {Request} req - Express request object
   * @param {Response} res - Express response object
   * @param {NextFunction} next - Express next function
   */
  deleteTask = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {
      const id = parseInt(req.params.id, 10);
      if (isNaN(id)) {
        throw new ApiError(400, 'Invalid task ID');
      }
      await this.taskService.deleteTask(id);
      res.status(204).send();
    } catch (error) {
      next(error);
    }
  };
}



