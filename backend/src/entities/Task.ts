import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, ManyToOne, JoinColumn } from 'typeorm';
import { User } from './User';

/**
 * Task status enumeration.
 */
export enum TaskStatus {
  PENDING = 'pending',
  IN_PROGRESS = 'in_progress',
  COMPLETED = 'completed'
}

/**
 * Task entity representing a task in the system.
 * Contains task information and relationship to user.
 */
@Entity('tasks')
export class Task {
  /**
   * Unique identifier for the task.
   * @type {number}
   */
  @PrimaryGeneratedColumn()
  id!: number;

  /**
   * Title of the task.
   * @type {string}
   */
  @Column()
  title!: string;

  /**
   * Detailed description of the task.
   * @type {string}
   */
  @Column({ type: 'text', nullable: true })
  description?: string;

  /**
   * Current status of the task.
   * @type {TaskStatus}
   */
  @Column({
    type: 'text',
    default: TaskStatus.PENDING
  })
  status!: TaskStatus;

  /**
   * ID of the user who owns this task.
   * @type {number}
   */
  @Column()
  userId!: number;

  /**
   * User who owns this task.
   * @type {User}
   */
  @ManyToOne(() => User, user => user.tasks, { onDelete: 'CASCADE' })
  @JoinColumn({ name: 'userId' })
  user!: User;

  /**
   * Timestamp when the task was created.
   * @type {Date}
   */
  @CreateDateColumn()
  createdAt!: Date;

  /**
   * Timestamp when the task was last updated.
   * @type {Date}
   */
  @UpdateDateColumn()
  updatedAt!: Date;
}



