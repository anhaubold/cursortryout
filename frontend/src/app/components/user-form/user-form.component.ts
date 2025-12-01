import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService, User } from '../../services/api.service';

/**
 * Component for creating and editing users.
 * Provides a form for user data input and validation.
 */
@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  /**
   * Reactive form for user data.
   * @type {FormGroup}
   */
  userForm!: FormGroup;

  /**
   * Current user ID if editing.
   * @type {number | null}
   */
  userId: number | null = null;

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

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  /**
   * Lifecycle hook: Initializes form and loads user data if editing.
   */
  ngOnInit(): void {
    this.initializeForm();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.userId = parseInt(id, 10);
      this.loadUser(this.userId);
    }
  }

  /**
   * Initializes the reactive form with validators.
   */
  initializeForm(): void {
    this.userForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  /**
   * Loads user data for editing.
   * @param {number} id - User ID
   */
  loadUser(id: number): void {
    this.loading = true;
    this.apiService.getUser(id).subscribe({
      next: (user) => {
        this.userForm.patchValue(user);
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message || 'Failed to load user';
        this.loading = false;
      }
    });
  }

  /**
   * Handles form submission.
   * Creates or updates user based on current mode.
   */
  onSubmit(): void {
    if (this.userForm.valid) {
      this.loading = true;
      this.error = null;
      this.success = null;

      const userData: User = this.userForm.value;

      if (this.userId) {
        this.updateUser(userData);
      } else {
        this.createUser(userData);
      }
    }
  }

  /**
   * Creates a new user.
   * @param {User} userData - User data from form
   */
  createUser(userData: User): void {
    this.apiService.createUser(userData).subscribe({
      next: () => {
        this.success = 'User created successfully';
        setTimeout(() => {
          this.router.navigate(['/users']);
        }, 1500);
      },
      error: (err) => {
        this.error = err.message || 'Failed to create user';
        this.loading = false;
      }
    });
  }

  /**
   * Updates an existing user.
   * @param {User} userData - Updated user data from form
   */
  updateUser(userData: User): void {
    this.apiService.updateUser(this.userId!, userData).subscribe({
      next: () => {
        this.success = 'User updated successfully';
        setTimeout(() => {
          this.router.navigate(['/users']);
        }, 1500);
      },
      error: (err) => {
        this.error = err.message || 'Failed to update user';
        this.loading = false;
      }
    });
  }

  /**
   * Cancels form editing and navigates back.
   */
  cancel(): void {
    this.router.navigate(['/users']);
  }
}



