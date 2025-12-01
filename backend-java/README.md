# Backend API (Java Spring Boot)

Java Spring Boot Backend mit vollständiger REST API für die Web-Anwendung.

## Technologie-Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** für Datenbankzugriffe
- **Hibernate** als JPA-Implementierung
- **SQLite** als Datenbank (kann einfach auf PostgreSQL/MySQL erweitert werden)
- **Maven** als Build-Tool

## Voraussetzungen

- Java 17 oder höher
- Maven 3.6 oder höher

## Installation

### 1. Projekt bauen

```bash
cd backend-java
mvn clean install
```

### 2. Anwendung starten

```bash
mvn spring-boot:run
```

Oder die JAR-Datei ausführen:

```bash
mvn package
java -jar target/backend-1.0.0.jar
```

Der Server läuft auf `http://localhost:3000`

## Entwicklung

### Mit Spring Boot DevTools

Die Anwendung unterstützt automatisches Neuladen bei Code-Änderungen:

```bash
mvn spring-boot:run
```

### Mit IDE

1. Öffnen Sie das Projekt in Ihrer IDE (IntelliJ IDEA, Eclipse, etc.)
2. Führen Sie die `BackendApplication` Klasse aus
3. Die Anwendung startet automatisch

## Konfiguration

Die Konfiguration erfolgt über `application.properties`:

- **Server Port**: `server.port=3000`
- **Datenbank**: SQLite (Datei: `./database.sqlite`)
- **CORS**: Konfiguriert für Frontend auf `http://localhost:4200`

### Umgebungsprofile

- **Development**: `application-dev.properties`
- **Production**: `application-prod.properties`

Aktivieren eines Profils:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## API Dokumentation

### Health Check

- `GET /health` - Server Status

### Users

- `GET /api/users` - Alle Benutzer abrufen
- `GET /api/users/:id` - Benutzer nach ID abrufen
- `POST /api/users` - Neuen Benutzer erstellen
- `PUT /api/users/:id` - Benutzer aktualisieren
- `DELETE /api/users/:id` - Benutzer löschen

#### Beispiel: Benutzer erstellen

```bash
curl -X POST http://localhost:3000/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "name": "John Doe"
  }'
```

### Tasks

- `GET /api/tasks` - Alle Tasks abrufen (optional: `?userId=123`)
- `GET /api/tasks/:id` - Task nach ID abrufen
- `POST /api/tasks` - Neuen Task erstellen
- `PUT /api/tasks/:id` - Task aktualisieren
- `PATCH /api/tasks/:id/status` - Task-Status aktualisieren
- `DELETE /api/tasks/:id` - Task löschen

#### Beispiel: Task erstellen

```bash
curl -X POST http://localhost:3000/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete project",
    "description": "Finish the backend implementation",
    "userId": 1,
    "status": "PENDING"
  }'
```

#### Beispiel: Task-Status aktualisieren

```bash
curl -X PATCH http://localhost:3000/api/tasks/1/status \
  -H "Content-Type: application/json" \
  -d '{
    "status": "IN_PROGRESS"
  }'
```

## Projektstruktur

```
src/
├── main/
│   ├── java/com/webapp/
│   │   ├── BackendApplication.java    # Main Application Class
│   │   ├── config/                     # Konfiguration (CORS, etc.)
│   │   ├── controller/                 # REST Controller
│   │   │   ├── UserController.java
│   │   │   ├── TaskController.java
│   │   │   └── HealthController.java
│   │   ├── dto/                        # Data Transfer Objects
│   │   │   ├── UserDto.java
│   │   │   ├── TaskDto.java
│   │   │   └── UpdateTaskStatusDto.java
│   │   ├── entity/                     # JPA Entities
│   │   │   ├── User.java
│   │   │   └── Task.java
│   │   ├── enums/                      # Enumerations
│   │   │   └── TaskStatus.java
│   │   ├── exception/                  # Exception Handling
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── ResourceNotFoundException.java
│   │   │   ├── ResourceConflictException.java
│   │   │   ├── InvalidRequestException.java
│   │   │   └── ErrorResponse.java
│   │   ├── repository/                 # Repository Interfaces
│   │   │   ├── UserRepository.java
│   │   │   └── TaskRepository.java
│   │   └── service/                    # Business Logic
│   │       ├── UserService.java
│   │       └── TaskService.java
│   └── resources/
│       ├── application.properties       # Hauptkonfiguration
│       ├── application-dev.properties  # Development Profile
│       └── application-prod.properties  # Production Profile
└── test/
    └── java/com/webapp/                 # Test-Klassen
```

## Architektur

Die Anwendung folgt einer klaren Schichtenarchitektur:

1. **Controller Layer**: REST Endpoints, Request/Response Handling
2. **Service Layer**: Geschäftslogik, Validierung
3. **Repository Layer**: Datenzugriff (Spring Data JPA)
4. **Entity Layer**: JPA Entities (Datenbank-Mapping)

### Datenfluss

```
HTTP Request
    ↓
Controller (validiert Request, konvertiert DTOs)
    ↓
Service (Geschäftslogik, Validierung)
    ↓
Repository (Datenbankzugriff)
    ↓
Database (SQLite)
```

## Datenbank

Die Anwendung verwendet SQLite als Datenbank. Die Datenbankdatei wird automatisch erstellt (`./database.sqlite`).

### Für Produktion

Für Produktionsumgebungen sollte eine robustere Datenbank verwendet werden:

1. **PostgreSQL**: 
   - Ändern Sie `spring.datasource.url` in `application-prod.properties`
   - Verwenden Sie `org.hibernate.dialect.PostgreSQLDialect`

2. **MySQL**:
   - Ändern Sie `spring.datasource.url` in `application-prod.properties`
   - Verwenden Sie `org.hibernate.dialect.MySQLDialect`

## Fehlerbehandlung

Die Anwendung verwendet ein zentrales Exception-Handling:

- **400 Bad Request**: Ungültige Anfrage (Validierungsfehler)
- **404 Not Found**: Ressource nicht gefunden
- **409 Conflict**: Ressourcenkonflikt (z.B. doppelte E-Mail)
- **500 Internal Server Error**: Unerwarteter Serverfehler

Alle Fehlerantworten folgen diesem Format:

```json
{
  "message": "Error message",
  "details": {},
  "timestamp": "2024-01-01T12:00:00"
}
```

## Tests

### Tests ausführen

```bash
mvn test
```

### Test-Coverage

Die Anwendung enthält Unit-Tests für:
- Service Layer (Geschäftslogik)
- Controller Layer (REST Endpoints)
- Repository Layer (Datenzugriff)

## Build & Deployment

### JAR erstellen

```bash
mvn clean package
```

Die JAR-Datei befindet sich in `target/backend-1.0.0.jar`

### Docker (optional)

Ein `Dockerfile` kann hinzugefügt werden für Container-Deployment.

## Unterschiede zum TypeScript Backend

Dieses Java Spring Boot Backend bietet die **gleiche Funktionalität** wie das TypeScript/Express Backend:

- ✅ Alle REST Endpoints identisch
- ✅ Gleiche Datenbankstruktur (SQLite)
- ✅ Gleiche Validierungsregeln
- ✅ Gleiche Fehlerbehandlung
- ✅ CORS konfiguriert für Angular Frontend

**Vorteile des Java Backends:**
- Starke Typisierung
- Enterprise-Ready Architektur
- Umfangreiches Ökosystem
- Bessere Performance bei hoher Last
- Integrierte Validierung und Sicherheit

## Lizenz

ISC


