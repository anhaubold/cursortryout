# Web Application

Eine vollständige Web-Anwendung mit Angular Frontend, Node.js/Express Backend und SQLite Datenbank.

## Projektstruktur

```
.
├── backend/          # Node.js/Express Backend mit TypeScript
├── frontend/         # Angular Frontend
└── README.md         # Diese Datei
```

## Technologie-Stack

### Backend
- **Node.js** mit **Express**
- **TypeScript** für typsicheren Code
- **TypeORM** als ORM für Datenbankzugriffe
- **SQLite** als Datenbank (kann einfach auf PostgreSQL/MySQL erweitert werden)

### Frontend
- **Angular 17** mit **TypeScript**
- **RxJS** für reaktive Programmierung
- **Reactive Forms** für Formularverwaltung

## Installation

### Backend

```bash
cd backend
npm install
```

Erstellen Sie eine `.env` Datei basierend auf `.env.example`:

```bash
cp .env.example .env
```

### Frontend

```bash
cd frontend
npm install
```

## Entwicklung

### Backend starten

```bash
cd backend
npm run dev
```

Das Backend läuft auf `http://localhost:3000`

### Frontend starten

```bash
cd frontend
npm start
```

Das Frontend läuft auf `http://localhost:4200`

## API Endpunkte

### Users
- `GET /api/users` - Alle Benutzer abrufen
- `GET /api/users/:id` - Benutzer nach ID abrufen
- `POST /api/users` - Neuen Benutzer erstellen
- `PUT /api/users/:id` - Benutzer aktualisieren
- `DELETE /api/users/:id` - Benutzer löschen

### Tasks
- `GET /api/tasks` - Alle Tasks abrufen (optional: `?userId=123`)
- `GET /api/tasks/:id` - Task nach ID abrufen
- `POST /api/tasks` - Neuen Task erstellen
- `PUT /api/tasks/:id` - Task aktualisieren
- `PATCH /api/tasks/:id/status` - Task-Status aktualisieren
- `DELETE /api/tasks/:id` - Task löschen

## Architektur

### Backend Architektur

```
backend/
├── src/
│   ├── config/          # Konfiguration (Datenbank, etc.)
│   ├── entities/        # TypeORM Entitäten
│   ├── repositories/    # Datenzugriffsschicht
│   ├── services/        # Geschäftslogik
│   ├── controllers/     # HTTP Request Handler
│   ├── routes/          # Route Definitionen
│   ├── middleware/      # Middleware (Error Handling, etc.)
│   └── server.ts        # Server Entry Point
```

**Schichtenarchitektur:**
- **Controller** → **Service** → **Repository** → **Entity**

### Frontend Architektur

```
frontend/src/app/
├── components/          # Angular Komponenten
│   ├── user-list/      # Benutzerliste
│   ├── user-form/      # Benutzerformular
│   ├── task-list/      # Taskliste
│   └── task-form/      # Taskformular
├── services/           # Angular Services
│   └── api.service.ts  # API Kommunikation
├── app.module.ts       # Root Module
└── app-routing.module.ts # Routing
```

## Datenbank

Die Anwendung verwendet SQLite als Standard-Datenbank. Die Datenbankdatei wird automatisch erstellt beim ersten Start.

### Entitäten

- **User**: Benutzer mit Email, Name und Tasks
- **Task**: Tasks mit Titel, Beschreibung, Status und Benutzerzuordnung

### Migrationen

```bash
# Migration erstellen
npm run migration:generate -- -n MigrationName

# Migration ausführen
npm run migration:run

# Migration rückgängig machen
npm run migration:revert
```

## Features

- ✅ Vollständige CRUD-Operationen für Users und Tasks
- ✅ Typsichere TypeScript-Implementierung
- ✅ RESTful API
- ✅ Fehlerbehandlung und Validierung
- ✅ Responsive UI
- ✅ Formularvalidierung
- ✅ Status-Management für Tasks

## Code-Qualität

- **Dokumentation**: Vollständige JSDoc/TSDoc Dokumentation
- **Type Safety**: Strikte TypeScript-Konfiguration
- **Clean Code**: Klare Struktur und Namenskonventionen
- **Error Handling**: Umfassende Fehlerbehandlung
- **Validation**: Input-Validierung auf allen Ebenen

## Erweiterungsmöglichkeiten

- Authentifizierung und Autorisierung (JWT)
- Pagination für Listen
- Filterung und Suche
- Datei-Upload
- Real-time Updates (WebSockets)
- Unit- und Integration-Tests
- Docker-Containerisierung
- CI/CD Pipeline

## Lizenz

ISC



