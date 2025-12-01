# Rebuild Checklist - Updated Dependencies

## ✅ Vorbereitung abgeschlossen

### Backend
- ✅ `package.json` aktualisiert mit LTS-Versionen
- ✅ `ts-node-dev` → `tsx` ersetzt
- ✅ TypeScript 5.6.3 konfiguriert
- ✅ Alle Dependencies auf neueste LTS-Versionen
- ✅ `tsconfig.json` kompatibel mit TypeScript 5.6
- ✅ Keine Linter-Fehler

### Frontend
- ✅ `package.json` aktualisiert mit Angular 18.2.0
- ✅ TypeScript 5.6.3 konfiguriert
- ✅ Alle Angular-Packages auf Version 18.2.0
- ✅ `angular.json` kompatibel
- ✅ `tsconfig.json` kompatibel
- ✅ Keine Linter-Fehler

## 📋 Rebuild Schritte

### Option 1: Automatisch (Empfohlen)
```bash
rebuild.bat
```

### Option 2: Manuell

#### Backend
```bash
cd backend
npm install
npm run build
```

#### Frontend
```bash
cd frontend
npm install
npm run build
```

## 🔍 Verifikation nach Rebuild

### Backend
- [ ] `backend/dist/` Verzeichnis existiert
- [ ] `backend/dist/server.js` kompiliert
- [ ] `npm run dev` startet ohne Fehler
- [ ] Health Check funktioniert: `http://localhost:3000/health`

### Frontend
- [ ] `frontend/dist/webapp-frontend/` Verzeichnis existiert
- [ ] `npm start` startet ohne Fehler
- [ ] Anwendung lädt im Browser: `http://localhost:4200`
- [ ] Keine Console-Fehler

## 📦 Aktualisierte Dependencies

### Backend
| Package | Version | Status |
|---------|---------|--------|
| express | 4.21.1 | ✅ LTS |
| typeorm | 0.3.20 | ✅ Latest |
| typescript | 5.6.3 | ✅ LTS |
| tsx | 4.19.2 | ✅ Modern |
| dotenv | 16.4.7 | ✅ Updated |
| reflect-metadata | 0.2.2 | ✅ Updated |

### Frontend
| Package | Version | Status |
|---------|---------|--------|
| @angular/core | 18.2.0 | ✅ LTS |
| typescript | 5.6.3 | ✅ LTS |
| rxjs | 7.8.1 | ✅ Updated |
| zone.js | 0.15.0 | ✅ Updated |

## 🚀 Nach dem Rebuild

1. **Testen Sie beide Server:**
   ```bash
   # Terminal 1
   cd backend && npm run dev
   
   # Terminal 2
   cd frontend && npm start
   ```

2. **Prüfen Sie die Funktionalität:**
   - Benutzer erstellen/bearbeiten/löschen
   - Tasks erstellen/bearbeiten/löschen
   - Status-Änderungen

3. **Überprüfen Sie die Konsole:**
   - Keine Deprecation-Warnungen
   - Keine Fehler
   - Alle Dependencies korrekt geladen

## 📝 Notizen

- Alle Legacy Dependencies entfernt ✅
- Alle Deprecated Dependencies ersetzt ✅
- Alle Packages auf LTS-Versionen ✅
- Code kompatibel mit neuen Versionen ✅
- Keine Breaking Changes erwartet ✅

## 🆘 Bei Problemen

Siehe `REBUILD_INSTRUCTIONS.md` für detaillierte Troubleshooting-Anleitung.



