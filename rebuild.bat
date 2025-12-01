@echo off
echo ========================================
echo Rebuilding Application with Updated Dependencies
echo ========================================
echo.

echo [1/4] Installing Backend Dependencies...
cd backend
if exist package-lock.json del package-lock.json
call npm install
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Backend dependencies installation failed!
    pause
    exit /b 1
)
echo Backend dependencies installed successfully.
echo.

echo [2/4] Building Backend...
call npm run build
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Backend build failed!
    pause
    exit /b 1
)
echo Backend built successfully.
echo.

cd ..
echo [3/4] Installing Frontend Dependencies...
cd frontend
if exist package-lock.json del package-lock.json
call npm install
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Frontend dependencies installation failed!
    pause
    exit /b 1
)
echo Frontend dependencies installed successfully.
echo.

echo [4/4] Building Frontend...
call npm run build
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Frontend build failed!
    pause
    exit /b 1
)
echo Frontend built successfully.
echo.

cd ..
echo ========================================
echo Rebuild completed successfully!
echo ========================================
echo.
echo Backend: backend/dist/
echo Frontend: frontend/dist/webapp-frontend/
echo.
pause



