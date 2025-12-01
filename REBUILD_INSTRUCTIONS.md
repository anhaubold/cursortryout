# Rebuild Instructions - Updated Dependencies

Diese Anleitung beschreibt, wie Sie die gesamte Anwendung mit den aktualisierten Dependencies neu aufbauen.

## Automatischer Rebuild (Empfohlen)

Führen Sie einfach aus:

```bash
rebuild.bat
```

Dieses Skript führt automatisch alle notwendigen Schritte aus.

## Manueller Rebuild

### Schritt 1: Backend Dependencies installieren

```bash
cd backend
npm install
```

**Erwartete Ausgabe:**
- Installation aller aktualisierten Packages
- `tsx` wird installiert (Ersatz für deprecated `ts-node-dev`)
- TypeORM 0.3.20, Express 4.21.1, TypeScript 5.6.3, etc.

### Schritt 2: Backend kompilieren

```bash
cd backend
npm run build
```

**Erwartete Ausgabe:**
- TypeScript wird kompiliert
- `dist/` Verzeichnis wird erstellt
- Alle `.ts` Dateien werden zu `.js` kompiliert

**Bei Fehlern:**
- Prüfen Sie TypeScript-Fehler in der Konsole
- Stellen Sie sicher, dass alle Imports korrekt sind

### Schritt 3: Frontend Dependencies installieren

```bash
cd frontend
npm install
```

**Erwartete Ausgabe:**
- Angular 18.2.0 wird installiert
- TypeScript 5.6.3 wird installiert
- Alle Angular-Packages werden aktualisiert

**Wichtige Änderungen:**
- Angular 17 → Angular 18 (LTS)
- Mögliche Breaking Changes beachten

### Schritt 4: Frontend kompilieren

```bash
cd frontend
npm run build
```

**Erwartete Ausgabe:**
- Angular Build wird ausgeführt
- `dist/webapp-frontend/` wird erstellt
- Production-Build wird generiert

**Bei Fehlern:**
- Prüfen Sie Angular-Kompatibilitätsfehler
- Stellen Sie sicher, dass alle Komponenten Angular 18 kompatibel sind

## Verifikation

### Backend Verifikation

1. **TypeScript Kompilierung prüfen:**
   ```bash
   cd backend
   npm run build
   ```
   Sollte ohne Fehler durchlaufen.

2. **Server starten (Test):**
   ```bash
   cd backend
   npm run dev
   ```
   Server sollte auf Port 3000 laufen.

3. **Health Check:**
   Öffnen Sie `http://localhost:3000/health`
   Sollte JSON mit Status "ok" zurückgeben.

### Frontend Verifikation

1. **Development Server:**
   ```bash
   cd frontend
   npm start
   ```
   Sollte auf Port 4200 laufen.

2. **Production Build:**
   ```bash
   cd frontend
   npm run build
   ```
   Sollte erfolgreich kompilieren.

3. **Browser Test:**
   Öffnen Sie `http://localhost:4200`
   Anwendung sollte ohne Fehler laden.

## Mögliche Probleme und Lösungen

### Problem 1: TypeScript Kompilierungsfehler

**Symptom:**
```
error TS2307: Cannot find module '...'
```

**Lösung:**
- Prüfen Sie, ob alle Imports korrekt sind
- Führen Sie `npm install` erneut aus
- Löschen Sie `node_modules` und `package-lock.json`, dann `npm install`

### Problem 2: Angular 18 Kompatibilitätsfehler

**Symptom:**
```
Error: The pipe 'xxx' could not be found
```

**Lösung:**
- Angular 18 hat einige Breaking Changes
- Prüfen Sie die [Angular 18 Migration Guide](https://angular.dev/update-guide)
- Stellen Sie sicher, dass alle Komponenten aktualisiert sind

### Problem 3: tsx nicht gefunden

**Symptom:**
```
'tsx' is not recognized as an internal or external command
```

**Lösung:**
```bash
cd backend
npm install tsx --save-dev
```

### Problem 4: TypeORM Fehler

**Symptom:**
```
Cannot find module 'typeorm'
```

**Lösung:**
```bash
cd backend
npm install typeorm@^0.3.20
```

## Clean Rebuild (Bei Problemen)

Falls Sie Probleme haben, führen Sie einen vollständigen Clean Rebuild durch:

### Backend Clean Rebuild

```bash
cd backend
rmdir /s /q node_modules
del package-lock.json
npm install
npm run build
```

### Frontend Clean Rebuild

```bash
cd frontend
rmdir /s /q node_modules
del package-lock.json
npm install
npm run build
```

## Erwartete Ergebnisse

Nach erfolgreichem Rebuild sollten Sie haben:

### Backend
- ✅ `backend/dist/` Verzeichnis mit kompilierten `.js` Dateien
- ✅ `backend/node_modules/` mit allen Dependencies
- ✅ `backend/package-lock.json` mit aktualisierten Versionen

### Frontend
- ✅ `frontend/dist/webapp-frontend/` mit Production Build
- ✅ `frontend/node_modules/` mit allen Dependencies
- ✅ `frontend/package-lock.json` mit aktualisierten Versionen

## Nächste Schritte

Nach erfolgreichem Rebuild:

1. **Testen Sie die Anwendung:**
   ```bash
   # Terminal 1: Backend
   cd backend && npm run dev
   
   # Terminal 2: Frontend
   cd frontend && npm start
   ```

2. **Prüfen Sie die Funktionalität:**
   - Erstellen Sie einen Benutzer
   - Erstellen Sie einen Task
   - Testen Sie alle CRUD-Operationen

3. **Überprüfen Sie die Konsole:**
   - Keine Deprecation-Warnungen
   - Keine Fehler
   - Alle Dependencies korrekt geladen

## Zusammenfassung der Updates

- ✅ **Backend**: Express 4.21.1, TypeORM 0.3.20, TypeScript 5.6.3, tsx statt ts-node-dev
- ✅ **Frontend**: Angular 18.2.0, TypeScript 5.6.3, RxJS 7.8.1
- ✅ **Alle Legacy Dependencies entfernt**
- ✅ **Alle Deprecated Dependencies ersetzt**

Die Anwendung ist jetzt vollständig auf LTS-Versionen aktualisiert!



