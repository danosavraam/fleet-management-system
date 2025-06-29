# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /build

COPY pom.xml car-simulator-service/
COPY src car-simulator-service/src

WORKDIR /build/car-simulator-service
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:21.0.7

WORKDIR /app

COPY --from=build /build/car-simulator-service/target/quarkus-app/ ./quarkus-app

ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]