import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, OneToMany } from 'typeorm';
import { Task } from './Task';

/**
 * User entity representing a user in the system.
 * Contains user information and relationships to tasks.
 */
@Entity('users')
export class User {
  /**
   * Unique identifier for the user.
   * @type {number}
   */
  @PrimaryGeneratedColumn()
  id!: number;

  /**
   * User's email address (unique).
   * @type {string}
   */
  @Column({ unique: true })
  email!: string;

  /**
   * User's full name.
   * @type {string}
   */
  @Column()
  name!: string;

  /**
   * Timestamp when the user was created.
   * @type {Date}
   */
  @CreateDateColumn()
  createdAt!: Date;

  /**
   * Timestamp when the user was last updated.
   * @type {Date}
   */
  @UpdateDateColumn()
  updatedAt!: Date;

  /**
   * Tasks associated with this user.
   * @type {Task[]}
   */
  @OneToMany(() => Task, task => task.user)
  tasks!: Task[];
}



