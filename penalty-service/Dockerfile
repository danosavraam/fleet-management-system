# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /build

COPY pom.xml penalty-service/
COPY src penalty-service/src

WORKDIR /build/penalty-service
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:21.0.7

WORKDIR /app

COPY --from=build /build/penalty-service/target/quarkus-app/ ./quarkus-app

ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]