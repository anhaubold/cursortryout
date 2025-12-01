import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

/**
 * API base URL.
 * Should be configured based on environment.
 */
const API_BASE_URL = 'http://localhost:3000/api';

/**
 * HTTP headers for API requests.
 */
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

/**
 * User interface representing a user entity.
 */
export interface User {
  id?: number;
  email: string;
  name: string;
  createdAt?: Date;
  updatedAt?: Date;
  tasks?: Task[];
}

/**
 * Task status enumeration.
 */
export enum TaskStatus {
  PENDING = 'pending',
  IN_PROGRESS = 'in_progress',
  COMPLETED = 'completed'
}

/**
 * Task interface representing a task entity.
 */
export interface Task {
  id?: number;
  title: string;
  description?: string;
  status: TaskStatus;
  userId: number;
  user?: User;
  createdAt?: Date;
  updatedAt?: Date;
}

/**
 * Service for API communication.
 * Handles all HTTP requests to the backend API.
 */
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) { }

  /**
   * Handles HTTP errors.
   * @param {HttpErrorResponse} error - The HTTP error response
   * @returns {Observable<never>} Error observable
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.error?.error?.message || error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }

  // User API methods

  /**
   * Retrieves all users.
   * @returns {Observable<User[]>} Observable of user array
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${API_BASE_URL}/users`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Retrieves a user by ID.
   * @param {number} id - User ID
   * @returns {Observable<User>} Observable of user
   */
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${API_BASE_URL}/users/${id}`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Creates a new user.
   * @param {User} user - User data
   * @returns {Observable<User>} Observable of created user
   */
  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${API_BASE_URL}/users`, user, httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Updates an existing user.
   * @param {number} id - User ID
   * @param {User} user - Updated user data
   * @returns {Observable<User>} Observable of updated user
   */
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${API_BASE_URL}/users/${id}`, user, httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Deletes a user by ID.
   * @param {number} id - User ID
   * @returns {Observable<void>} Observable of void
   */
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${API_BASE_URL}/users/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Task API methods

  /**
   * Retrieves all tasks, optionally filtered by user ID.
   * @param {number} userId - Optional user ID filter
   * @returns {Observable<Task[]>} Observable of task array
   */
  getTasks(userId?: number): Observable<Task[]> {
    const url = userId ? `${API_BASE_URL}/tasks?userId=${userId}` : `${API_BASE_URL}/tasks`;
    return this.http.get<Task[]>(url)
      .pipe(catchError(this.handleError));
  }

  /**
   * Retrieves a task by ID.
   * @param {number} id - Task ID
   * @returns {Observable<Task>} Observable of task
   */
  getTask(id: number): Observable<Task> {
    return this.http.get<Task>(`${API_BASE_URL}/tasks/${id}`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Creates a new task.
   * @param {Task} task - Task data
   * @returns {Observable<Task>} Observable of created task
   */
  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${API_BASE_URL}/tasks`, task, httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Updates an existing task.
   * @param {number} id - Task ID
   * @param {Task} task - Updated task data
   * @returns {Observable<Task>} Observable of updated task
   */
  updateTask(id: number, task: Task): Observable<Task> {
    return this.http.put<Task>(`${API_BASE_URL}/tasks/${id}`, task, httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Updates task status.
   * @param {number} id - Task ID
   * @param {TaskStatus} status - New status
   * @returns {Observable<Task>} Observable of updated task
   */
  updateTaskStatus(id: number, status: TaskStatus): Observable<Task> {
    return this.http.patch<Task>(`${API_BASE_URL}/tasks/${id}/status`, { status }, httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Deletes a task by ID.
   * @param {number} id - Task ID
   * @returns {Observable<void>} Observable of void
   */
  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${API_BASE_URL}/tasks/${id}`)
      .pipe(catchError(this.handleError));
  }
}



