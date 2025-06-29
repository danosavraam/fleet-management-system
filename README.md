# Fleet Management System (FMS)

> This is a microservices-based backend platform designed to manage a fleet of vehicles and monitor driver behavior in real time. It provides tools to register and track cars, drivers, and trips, simulate live driving conditions, and automatically apply penalties based on unsafe driving patterns.

> The project uses **Quarkus** as the **Java** framework and Docker for local development.

---

## Tech Stack

- Java 21
- Quarkus
- Maven
- PostgreSQL
- Hibernate ORM with Panache
- MapStruct (for DTO mapping)
- Flyway (for DB migrations)
- Docker + Docker Compose
- Swagger UI / OpenAPI

---

## Fleet Service (`fleet-service`)

### Features

- CRUD operations for **Drivers**, **Cars**, and **Trips**
- Driver ↔ Car one-to-one assignment logic
- Trip lifecycle management including start, end, and distance
- DTOs with validation annotations
- Business logic encapsulated in a service layer
- Clean architecture with entity/DTO separation
- OpenAPI documentation via Swagger UI
- Database versioning with Flyway

---

## API Documentation

- **Swagger UI**: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)
- **OpenAPI Spec**: [http://localhost:8080/openapi](http://localhost:8080/openapi)

---

## PostgreSQL + PgAdmin Setup

Use Docker Compose to start PostgreSQL and PgAdmin:

```
docker-compose up --build
```

- PostgreSQL: `localhost:5432`, for docker: `postgres:5432`, user: `fms`, password: `secret`, database: `fleetdb`
- PgAdmin: [http://localhost:8081](http://localhost:8081), login: `admin@fms.dev` / `admin`

---

## Running the Service

### Using Maven
```
mvn clean package
java -jar target/fleet-service.jar
```

### Using Dockerfile
Build the image:
```
docker build -t fleet-service .
```

Run the container:
```
docker run -p 8080:8080 fleet-service
```

---

## Notes

- Database schema is managed via Flyway migrations (`src/main/resources/db/migration`)
- Entities are mapped with JPA annotations and follow domain-driven design
- DTOs are kept separate from entities for clarity and flexibility

---

## Directory Structure (main packages)

```
com.fms.fleet
├── controller      # REST endpoints
├── domain          # Entity classes
├── dto             # Data Transfer Objects
├── mapper          # MapStruct mappers
├── service         # Service logic
├── exception       # Custom exceptions and mappers
```

---

## Car Simulator Service (`car-simulator-service`)

### Features

- Scheduled generation of heartbeat data (car ID, location, speed, timestamp)
- Fetches driver info from `fleet-service` via REST
- Publishes to Kafka topic `heartbeat-events` in JSON format
- Configurable via `application.properties`

## Running the Service

### Using Maven
```
mvn clean package
java -jar target/car-simulator-service.jar
```

### Using Dockerfile
Build the image:
```
docker build -t car-simulator-service .
```

Run the container:
```
docker run car-simulator-service
```

---

Ensure Kafka and `fleet-service` are running and healthy before starting the simulator.

### Kafka Integration

- Topic: `heartbeat-events`
- Format: JSON
- Serializer: `ObjectMapperSerializer`
- Kafka bootstrap: `kafka:9092` (Docker network)

You can inspect messages using Kafdrop (http://localhost:9000)

---

## Directory Structure (main packages)

```
com.fms.simulator
├── client          # REST client
├── model           # Model classes
├── service         # Service logic
```

---