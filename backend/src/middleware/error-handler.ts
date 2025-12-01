import { Request, Response, NextFunction } from 'express';

/**
 * Custom error class for API errors.
 * Extends Error with status code and optional details.
 */
export class ApiError extends Error {
  /**
   * HTTP status code for the error.
   * @type {number}
   */
  public statusCode: number;

  /**
   * Additional error details.
   * @type {any}
   */
  public details?: any;

  /**
   * Creates a new API error.
   * @param {number} statusCode - HTTP status code
   * @param {string} message - Error message
   * @param {any} details - Optional error details
   */
  constructor(statusCode: number, message: string, details?: any) {
    super(message);
    this.statusCode = statusCode;
    this.details = details;
    this.name = 'ApiError';
    Error.captureStackTrace(this, this.constructor);
  }
}

/**
 * Global error handler middleware.
 * Handles all errors and returns appropriate HTTP responses.
 * 
 * @param {Error} err - The error object
 * @param {Request} req - Express request object
 * @param {Response} res - Express response object
 * @param {NextFunction} next - Express next function
 */
export function errorHandler(
  err: Error | ApiError,
  req: Request,
  res: Response,
  next: NextFunction
): void {
  if (err instanceof ApiError) {
    res.status(err.statusCode).json({
      error: {
        message: err.message,
        details: err.details,
        timestamp: new Date().toISOString()
      }
    });
  } else {
    console.error('Unhandled error:', err);
    res.status(500).json({
      error: {
        message: 'Internal server error',
        timestamp: new Date().toISOString()
      }
    });
  }
}



