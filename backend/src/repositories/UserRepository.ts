import { Repository } from 'typeorm';
import { AppDataSource } from '../config/data-source';
import { User } from '../entities/User';
import { ApiError } from '../middleware/error-handler';

/**
 * Repository for User entity operations.
 * Provides data access methods for user management.
 */
export class UserRepository {
  private repository: Repository<User>;

  constructor() {
    this.repository = AppDataSource.getRepository(User);
  }

  /**
   * Finds all users.
   * @returns {Promise<User[]>} Array of all users
   */
  async findAll(): Promise<User[]> {
    return await this.repository.find({
      relations: ['tasks']
    });
  }

  /**
   * Finds a user by ID.
   * @param {number} id - User ID
   * @returns {Promise<User>} User entity
   * @throws {ApiError} If user not found
   */
  async findById(id: number): Promise<User> {
    const user = await this.repository.findOne({
      where: { id },
      relations: ['tasks']
    });

    if (!user) {
      throw new ApiError(404, `User with ID ${id} not found`);
    }

    return user;
  }

  /**
   * Finds a user by email.
   * @param {string} email - User email
   * @returns {Promise<User | null>} User entity or null if not found
   */
  async findByEmail(email: string): Promise<User | null> {
    return await this.repository.findOne({
      where: { email },
      relations: ['tasks']
    });
  }

  /**
   * Creates a new user.
   * @param {Partial<User>} userData - User data
   * @returns {Promise<User>} Created user entity
   * @throws {ApiError} If email already exists
   */
  async create(userData: Partial<User>): Promise<User> {
    const existingUser = await this.findByEmail(userData.email!);
    if (existingUser) {
      throw new ApiError(409, `User with email ${userData.email} already exists`);
    }

    const user = this.repository.create(userData);
    return await this.repository.save(user);
  }

  /**
   * Updates an existing user.
   * @param {number} id - User ID
   * @param {Partial<User>} userData - Updated user data
   * @returns {Promise<User>} Updated user entity
   * @throws {ApiError} If user not found
   */
  async update(id: number, userData: Partial<User>): Promise<User> {
    const user = await this.findById(id);
    Object.assign(user, userData);
    return await this.repository.save(user);
  }

  /**
   * Deletes a user by ID.
   * @param {number} id - User ID
   * @throws {ApiError} If user not found
   */
  async delete(id: number): Promise<void> {
    const user = await this.findById(id);
    await this.repository.remove(user);
  }
}



