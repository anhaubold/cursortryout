import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService, Task, TaskStatus } from '../../services/api.service';

/**
 * Component for displaying and managing a list of tasks.
 * Provides functionality to view, create, edit, delete, and update task status.
 */
@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  /**
   * Array of tasks to display.
   * @type {Task[]}
   */
  tasks: Task[] = [];

  /**
   * Loading state indicator.
   * @type {boolean}
   */
  loading = false;

  /**
   * Error message to display.
   * @type {string | null}
   */
  error: string | null = null;

  /**
   * Task status enumeration for template access.
   * @type {typeof TaskStatus}
   */
  TaskStatus = TaskStatus;

  constructor(
    private apiService: ApiService,
    private router: Router
  ) { }

  /**
   * Lifecycle hook: Initializes component and loads tasks.
   */
  ngOnInit(): void {
    this.loadTasks();
  }

  /**
   * Loads all tasks from the API.
   */
  loadTasks(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getTasks().subscribe({
      next: (tasks) => {
        this.tasks = tasks;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message || 'Failed to load tasks';
        this.loading = false;
      }
    });
  }

  /**
   * Navigates to the task creation form.
   */
  createTask(): void {
    this.router.navigate(['/tasks/new']);
  }

  /**
   * Navigates to the task edit form.
   * @param {number} id - Task ID
   */
  editTask(id: number): void {
    this.router.navigate([`/tasks/${id}/edit`]);
  }

  /**
   * Updates task status.
   * @param {number} id - Task ID
   * @param {TaskStatus} status - New status
   */
  updateStatus(id: number, status: TaskStatus): void {
    this.apiService.updateTaskStatus(id, status).subscribe({
      next: () => {
        this.loadTasks();
      },
      error: (err) => {
        this.error = err.message || 'Failed to update task status';
      }
    });
  }

  /**
   * Deletes a task after confirmation.
   * @param {number} id - Task ID
   */
  deleteTask(id: number): void {
    if (confirm('Are you sure you want to delete this task?')) {
      this.apiService.deleteTask(id).subscribe({
        next: () => {
          this.loadTasks();
        },
        error: (err) => {
          this.error = err.message || 'Failed to delete task';
        }
      });
    }
  }

  /**
   * Gets the display label for a task status.
   * @param {TaskStatus} status - Task status
   * @returns {string} Status label
   */
  getStatusLabel(status: TaskStatus): string {
    return status.charAt(0).toUpperCase() + status.slice(1).replace('_', ' ');
  }
}



