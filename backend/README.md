# Backend API

Node.js/Express Backend mit TypeScript für die Web-Anwendung.

## Installation

```bash
npm install
```

## Konfiguration

Kopieren Sie `.env.example` zu `.env` und passen Sie die Werte an:

```bash
cp .env.example .env
```

## Entwicklung

```bash
npm run dev
```

Der Server läuft auf `http://localhost:3000`

## Build

```bash
npm run build
npm start
```

## API Dokumentation

### Health Check
- `GET /health` - Server Status

### Users
- `GET /api/users` - Alle Benutzer
- `GET /api/users/:id` - Benutzer nach ID
- `POST /api/users` - Neuen Benutzer erstellen
- `PUT /api/users/:id` - Benutzer aktualisieren
- `DELETE /api/users/:id` - Benutzer löschen

### Tasks
- `GET /api/tasks` - Alle Tasks (optional: `?userId=123`)
- `GET /api/tasks/:id` - Task nach ID
- `POST /api/tasks` - Neuen Task erstellen
- `PUT /api/tasks/:id` - Task aktualisieren
- `PATCH /api/tasks/:id/status` - Task-Status aktualisieren
- `DELETE /api/tasks/:id` - Task löschen

## Projektstruktur

```
src/
├── config/          # Datenbankkonfiguration
├── entities/        # TypeORM Entitäten
├── repositories/    # Datenzugriffsschicht
├── services/        # Geschäftslogik
├── controllers/     # HTTP Handler
├── routes/          # Route Definitionen
├── middleware/      # Middleware
└── server.ts        # Entry Point
```

## Datenbank

Die Anwendung verwendet SQLite. Die Datenbank wird automatisch erstellt.

Für Produktion sollte eine PostgreSQL oder MySQL Datenbank verwendet werden.



