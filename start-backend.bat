@echo off
echo Starting Backend Server...
cd backend
if not exist node_modules (
    echo Installing dependencies...
    call npm install
)
if not exist .env (
    echo Creating .env file...
    (
        echo PORT=3000
        echo NODE_ENV=development
        echo DB_TYPE=sqlite
        echo DB_DATABASE=./database.sqlite
        echo CORS_ORIGIN=http://localhost:4200
    ) > .env
)
echo Starting backend server on http://localhost:3000
call npm run dev



