import { UserRepository } from '../repositories/UserRepository';
import { User } from '../entities/User';
import { ApiError } from '../middleware/error-handler';

/**
 * Service for user business logic.
 * Handles user-related operations and validation.
 */
export class UserService {
  private userRepository: UserRepository;

  constructor() {
    this.userRepository = new UserRepository();
  }

  /**
   * Retrieves all users.
   * @returns {Promise<User[]>} Array of all users
   */
  async getAllUsers(): Promise<User[]> {
    return await this.userRepository.findAll();
  }

  /**
   * Retrieves a user by ID.
   * @param {number} id - User ID
   * @returns {Promise<User>} User entity
   * @throws {ApiError} If user not found
   */
  async getUserById(id: number): Promise<User> {
    return await this.userRepository.findById(id);
  }

  /**
   * Creates a new user with validation.
   * @param {Partial<User>} userData - User data
   * @returns {Promise<User>} Created user entity
   * @throws {ApiError} If validation fails or email exists
   */
  async createUser(userData: Partial<User>): Promise<User> {
    this.validateUserData(userData);
    return await this.userRepository.create(userData);
  }

  /**
   * Updates an existing user.
   * @param {number} id - User ID
   * @param {Partial<User>} userData - Updated user data
   * @returns {Promise<User>} Updated user entity
   * @throws {ApiError} If user not found or validation fails
   */
  async updateUser(id: number, userData: Partial<User>): Promise<User> {
    if (userData.email) {
      this.validateEmail(userData.email);
    }
    return await this.userRepository.update(id, userData);
  }

  /**
   * Deletes a user by ID.
   * @param {number} id - User ID
   * @throws {ApiError} If user not found
   */
  async deleteUser(id: number): Promise<void> {
    await this.userRepository.delete(id);
  }

  /**
   * Validates user data.
   * @param {Partial<User>} userData - User data to validate
   * @throws {ApiError} If validation fails
   */
  private validateUserData(userData: Partial<User>): void {
    if (!userData.email) {
      throw new ApiError(400, 'Email is required');
    }
    if (!userData.name) {
      throw new ApiError(400, 'Name is required');
    }
    this.validateEmail(userData.email);
  }

  /**
   * Validates email format.
   * @param {string} email - Email to validate
   * @throws {ApiError} If email format is invalid
   */
  private validateEmail(email: string): void {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      throw new ApiError(400, 'Invalid email format');
    }
  }
}



