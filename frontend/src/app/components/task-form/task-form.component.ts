import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService, Task, TaskStatus, User } from '../../services/api.service';

/**
 * Component for creating and editing tasks.
 * Provides a form for task data input and validation.
 */
@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {
  /**
   * Reactive form for task data.
   * @type {FormGroup}
   */
  taskForm!: FormGroup;

  /**
   * Current task ID if editing.
   * @type {number | null}
   */
  taskId: number | null = null;

  /**
   * Array of available users for task assignment.
   * @type {User[]}
   */
  users: User[] = [];

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
   * Success message to display.
   * @type {string | null}
   */
  success: string | null = null;

  /**
   * Task status enumeration for template access.
   * @type {typeof TaskStatus}
   */
  TaskStatus = TaskStatus;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  /**
   * Lifecycle hook: Initializes form and loads data.
   */
  ngOnInit(): void {
    this.initializeForm();
    this.loadUsers();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.taskId = parseInt(id, 10);
      this.loadTask(this.taskId);
    }
  }

  /**
   * Initializes the reactive form with validators.
   */
  initializeForm(): void {
    this.taskForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      description: [''],
      status: [TaskStatus.PENDING, Validators.required],
      userId: [null, Validators.required]
    });
  }

  /**
   * Loads all users for the user selection dropdown.
   */
  loadUsers(): void {
    this.apiService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (err) => {
        this.error = err.message || 'Failed to load users';
      }
    });
  }

  /**
   * Loads task data for editing.
   * @param {number} id - Task ID
   */
  loadTask(id: number): void {
    this.loading = true;
    this.apiService.getTask(id).subscribe({
      next: (task) => {
        this.taskForm.patchValue(task);
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message || 'Failed to load task';
        this.loading = false;
      }
    });
  }

  /**
   * Handles form submission.
   * Creates or updates task based on current mode.
   */
  onSubmit(): void {
    if (this.taskForm.valid) {
      this.loading = true;
      this.error = null;
      this.success = null;

      const taskData: Task = this.taskForm.value;

      if (this.taskId) {
        this.updateTask(taskData);
      } else {
        this.createTask(taskData);
      }
    }
  }

  /**
   * Creates a new task.
   * @param {Task} taskData - Task data from form
   */
  createTask(taskData: Task): void {
    this.apiService.createTask(taskData).subscribe({
      next: () => {
        this.success = 'Task created successfully';
        setTimeout(() => {
          this.router.navigate(['/tasks']);
        }, 1500);
      },
      error: (err) => {
        this.error = err.message || 'Failed to create task';
        this.loading = false;
      }
    });
  }

  /**
   * Updates an existing task.
   * @param {Task} taskData - Updated task data from form
   */
  updateTask(taskData: Task): void {
    this.apiService.updateTask(this.taskId!, taskData).subscribe({
      next: () => {
        this.success = 'Task updated successfully';
        setTimeout(() => {
          this.router.navigate(['/tasks']);
        }, 1500);
      },
      error: (err) => {
        this.error = err.message || 'Failed to update task';
        this.loading = false;
      }
    });
  }

  /**
   * Cancels form editing and navigates back.
   */
  cancel(): void {
    this.router.navigate(['/tasks']);
  }
}



