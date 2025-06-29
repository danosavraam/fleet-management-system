# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /build

COPY pom.xml fleet-service/
COPY src fleet-service/src

WORKDIR /build/fleet-service
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:21.0.7

WORKDIR /app

COPY --from=build /build/fleet-service/target/quarkus-app/ ./quarkus-app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]