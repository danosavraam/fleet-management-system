# Changelog

## [1.0.0] - 2025-06-29

### Features

- **Penalty Service**
    - Kafka consumer for `heartbeat-events`
    - Calculates penalties based on driver speed violations
    - Stores penalty points in Redis
    - REST API to retrieve and reset driver penalties

- **Car Simulator Service**
    - Simulates real-time driver heartbeats with random speeds
    - Sends heartbeat messages to Kafka topic (`heartbeat-events`)
    - Supports scheduling of events

- **Fleet Service**
    - Manages driver registration and data persistence in PostgreSQL
    - Provides REST API to create, retrieve, update and delete data
    - Flyway integration for schema management

- **Kafka Integration**
    - Configured `heartbeat-events` topic
    - Kafka messaging with SmallRye Reactive Messaging
    - Dockerized

- **Redis Integration**
    - Used by Penalty Service for quick access to penalty data
    - Redis UI support via Redis Commander

- **Docker Compose**
    - Unified stack with services: `postgres`, `pgadmin`, `kafka`, `kafdrop`, `redis`, `redis-ui`, `fleet-service`, `car-simulator-service`, `penalty-service`

---