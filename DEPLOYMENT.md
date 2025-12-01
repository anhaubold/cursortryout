# Deployment Anleitung - Localhost

Diese Anleitung beschreibt, wie Sie die Anwendung auf localhost deployen.

## Schnellstart

### Option 1: Automatisches Start-Skript (Windows)

Führen Sie einfach aus:

```bash
start-all.bat
```

Dies startet automatisch beide Server (Backend und Frontend) in separaten Fenstern.

### Option 2: Manueller Start

#### Schritt 1: Backend starten

```bash
cd backend
npm install
```

Erstellen Sie eine `.env` Datei im `backend` Verzeichnis:

```env
PORT=3000
NODE_ENV=development
DB_TYPE=sqlite
DB_DATABASE=./database.sqlite
CORS_ORIGIN=http://localhost:4200
```

Starten Sie den Backend-Server:

```bash
npm run dev
```

Das Backend läuft nun auf `http://localhost:3000`

#### Schritt 2: Frontend starten (in einem neuen Terminal)

```bash
cd frontend
npm install
npm start
```

Das Frontend läuft nun auf `http://localhost:4200`

## Verifikation

### Backend Health Check

Öffnen Sie in Ihrem Browser:
```
http://localhost:3000/health
```

Sie sollten eine JSON-Antwort sehen:
```json
{
  "status": "ok",
  "timestamp": "2024-01-01T12:00:00.000Z"
}
```

### Frontend

Öffnen Sie in Ihrem Browser:
```
http://localhost:4200
```

Sie sollten die Web-Anwendung sehen mit Navigation zu "Users" und "Tasks".

## Troubleshooting

### Port bereits belegt

Wenn Port 3000 oder 4200 bereits belegt ist:

**Backend:**
- Ändern Sie `PORT=3000` in der `.env` Datei zu einem anderen Port (z.B. `PORT=3001`)
- Passen Sie `CORS_ORIGIN` entsprechend an

**Frontend:**
- Angular verwendet standardmäßig Port 4200
- Um einen anderen Port zu verwenden: `ng serve --port 4201`
- Passen Sie dann `CORS_ORIGIN` im Backend an

### Datenbank-Fehler

Die SQLite-Datenbank wird automatisch beim ersten Start erstellt. Falls Probleme auftreten:

1. Löschen Sie `backend/database.sqlite` (falls vorhanden)
2. Starten Sie den Backend-Server neu

### CORS-Fehler

Stellen Sie sicher, dass:
- `CORS_ORIGIN` in der Backend `.env` Datei auf `http://localhost:4200` gesetzt ist
- Beide Server laufen
- Keine Firewall die Verbindung blockiert

### Dependencies-Fehler

Falls `npm install` fehlschlägt:

```bash
# Backend
cd backend
rm -rf node_modules package-lock.json
npm install

# Frontend
cd frontend
rm -rf node_modules package-lock.json
npm install
```

## Produktions-Build

### Backend Build

```bash
cd backend
npm run build
npm start
```

### Frontend Build

```bash
cd frontend
npm run build
```

Die gebauten Dateien befinden sich in `frontend/dist/webapp-frontend/`

## Nächste Schritte

Nach erfolgreichem Start können Sie:

1. **Benutzer erstellen**: Navigieren Sie zu "Users" → "Create New User"
2. **Tasks erstellen**: Navigieren Sie zu "Tasks" → "Create New Task"
3. **Daten verwalten**: Bearbeiten und löschen Sie Einträge über die UI

## API Testen

Sie können die API auch direkt testen mit Tools wie:
- **Postman**
- **curl**
- **Thunder Client** (VS Code Extension)

Beispiel mit curl:

```bash
# Health Check
curl http://localhost:3000/health

# Alle Benutzer abrufen
curl http://localhost:3000/api/users

# Neuen Benutzer erstellen
curl -X POST http://localhost:3000/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'
```



