@echo off
echo Starting Frontend Server...
cd frontend
if not exist node_modules (
    echo Installing dependencies...
    call npm install
)
echo Starting frontend server on http://localhost:4200
call npm start



