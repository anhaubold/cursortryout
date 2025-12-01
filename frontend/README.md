# Web Application Frontend

Angular frontend application for the web application.

## Prerequisites

- Node.js (v18 or higher)
- npm or yarn

## Installation

```bash
npm install
```

## Development

Start the development server:

```bash
npm start
```

The application will be available at `http://localhost:4200`.

## Build

Build for production:

```bash
npm run build
```

## Project Structure

```
src/
├── app/
│   ├── components/          # Angular components
│   │   ├── user-list/       # User list component
│   │   ├── user-form/       # User form component
│   │   ├── task-list/       # Task list component
│   │   └── task-form/       # Task form component
│   ├── services/            # Angular services
│   │   └── api.service.ts   # API communication service
│   ├── app.component.ts     # Root component
│   ├── app.module.ts        # Root module
│   └── app-routing.module.ts # Routing configuration
├── styles.css               # Global styles
└── main.ts                  # Application entry point
```



