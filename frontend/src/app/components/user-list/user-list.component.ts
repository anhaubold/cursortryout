import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService, User } from '../../services/api.service';

/**
 * Component for displaying and managing a list of users.
 * Provides functionality to view, create, edit, and delete users.
 */
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  /**
   * Array of users to display.
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

  constructor(
    private apiService: ApiService,
    private router: Router
  ) { }

  /**
   * Lifecycle hook: Initializes component and loads users.
   */
  ngOnInit(): void {
    this.loadUsers();
  }

  /**
   * Loads all users from the API.
   */
  loadUsers(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message || 'Failed to load users';
        this.loading = false;
      }
    });
  }

  /**
   * Navigates to the user creation form.
   */
  createUser(): void {
    this.router.navigate(['/users/new']);
  }

  /**
   * Navigates to the user edit form.
   * @param {number} id - User ID
   */
  editUser(id: number): void {
    this.router.navigate([`/users/${id}/edit`]);
  }

  /**
   * Deletes a user after confirmation.
   * @param {number} id - User ID
   */
  deleteUser(id: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.apiService.deleteUser(id).subscribe({
        next: () => {
          this.loadUsers();
        },
        error: (err) => {
          this.error = err.message || 'Failed to delete user';
        }
      });
    }
  }
}



