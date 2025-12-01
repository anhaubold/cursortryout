import express, { Application, Request, Response, NextFunction } from 'express';
import cors from 'cors';
import dotenv from 'dotenv';
import { AppDataSource } from './config/data-source';
import { errorHandler } from './middleware/error-handler';
import { apiRoutes } from './routes';

/**
 * Main server application.
 * Initializes Express server, database connection, and middleware.
 */
class Server {
  private app: Application;
  private port: number;

  constructor() {
    this.app = express();
    this.port = parseInt(process.env.PORT || '3000', 10);
    this.initializeMiddleware();
    this.initializeRoutes();
    this.initializeErrorHandling();
  }

  /**
   * Initializes Express middleware.
   * Configures CORS, JSON parsing, and URL encoding.
   */
  private initializeMiddleware(): void {
    this.app.use(cors({
      origin: process.env.CORS_ORIGIN || 'http://localhost:4200',
      credentials: true
    }));
    this.app.use(express.json());
    this.app.use(express.urlencoded({ extended: true }));
  }

  /**
   * Initializes API routes.
   * Mounts all route handlers under /api prefix.
   */
  private initializeRoutes(): void {
    this.app.get('/health', (req: Request, res: Response) => {
      res.json({ status: 'ok', timestamp: new Date().toISOString() });
    });
    this.app.use('/api', apiRoutes);
  }

  /**
   * Initializes error handling middleware.
   * Must be called after all routes.
   */
  private initializeErrorHandling(): void {
    this.app.use(errorHandler);
  }

  /**
   * Initializes database connection and starts the server.
   * @throws {Error} If database connection fails
   */
  public async start(): Promise<void> {
    try {
      await AppDataSource.initialize();
      console.log('Database connection established');

      this.app.listen(this.port, () => {
        console.log(`Server running on port ${this.port}`);
        console.log(`Environment: ${process.env.NODE_ENV || 'development'}`);
      });
    } catch (error) {
      console.error('Failed to start server:', error);
      process.exit(1);
    }
  }
}

// Load environment variables
dotenv.config();

// Start server
const server = new Server();
server.start();



