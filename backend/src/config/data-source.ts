import { DataSource, DataSourceOptions } from 'typeorm';
import { User } from '../entities/User';
import { Task } from '../entities/Task';

/**
 * Database configuration options.
 * Configures TypeORM connection settings based on environment variables.
 */
const dataSourceOptions: DataSourceOptions = {
  type: (process.env.DB_TYPE as any) || 'sqlite',
  database: process.env.DB_DATABASE || './database.sqlite',
  synchronize: process.env.NODE_ENV !== 'production',
  logging: process.env.NODE_ENV === 'development',
  entities: [User, Task],
  migrations: ['src/migrations/**/*.ts'],
  subscribers: ['src/subscribers/**/*.ts']
};

/**
 * TypeORM DataSource instance.
 * Used for database operations and migrations.
 */
export const AppDataSource = new DataSource(dataSourceOptions);



