@echo off
echo Starting Web Application...
echo.
echo Starting Backend Server...
start "Backend Server" cmd /k "cd backend && if not exist node_modules (npm install) && if not exist .env ((echo PORT=3000 && echo NODE_ENV=development && echo DB_TYPE=sqlite && echo DB_DATABASE=./database.sqlite && echo CORS_ORIGIN=http://localhost:4200) > .env) && npm run dev"
timeout /t 5 /nobreak >nul
echo.
echo Starting Frontend Server...
start "Frontend Server" cmd /k "cd frontend && if not exist node_modules (npm install) && npm start"
echo.
echo Both servers are starting...
echo Backend: http://localhost:3000
echo Frontend: http://localhost:4200
pause



