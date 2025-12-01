import { Router } from 'express';
import { UserController } from '../controllers/UserController';
import { TaskController } from '../controllers/TaskController';

/**
 * Main router for API routes.
 * Combines all route handlers and exports as a single router.
 */
const router = Router();
const userController = new UserController();
const taskController = new TaskController();

// User routes
router.get('/users', userController.getAllUsers);
router.get('/users/:id', userController.getUserById);
router.post('/users', userController.createUser);
router.put('/users/:id', userController.updateUser);
router.delete('/users/:id', userController.deleteUser);

// Task routes
router.get('/tasks', taskController.getAllTasks);
router.get('/tasks/:id', taskController.getTaskById);
router.post('/tasks', taskController.createTask);
router.put('/tasks/:id', taskController.updateTask);
router.patch('/tasks/:id/status', taskController.updateTaskStatus);
router.delete('/tasks/:id', taskController.deleteTask);

export { router as apiRoutes };



