package com.fms.simulator.service;

import com.fms.simulator.client.DriverServiceClient;
import com.fms.simulator.model.Heartbeat;
import com.fms.simulator.model.Driver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import io.quarkus.scheduler.Scheduled;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class HeartbeatService {

    @Inject
    @RestClient
    DriverServiceClient driverServiceClient;

    @Inject
    @Channel("heartbeat-out")
    Emitter<Heartbeat> heartbeatEmitter;

    private static final Logger LOG = Logger.getLogger(HeartbeatService.class);
    private final Random random = new Random();

    @Scheduled(every = "5s")
    public void emitHeartbeat() {
        try {
            List<Driver> drivers = driverServiceClient.getAllDrivers();

            // Skip if drivers table is empty
            if (drivers == null || drivers.isEmpty()) {
                return;
            }

            Driver selected = drivers.get(random.nextInt(drivers.size()));

            // Skip if car's plate number is null or blank
            if (selected.carPlateNumber == null || selected.carPlateNumber.isBlank()) {
                return;
            }

            Heartbeat heartbeat = new Heartbeat();
            heartbeat.carId = selected.carPlateNumber;
            heartbeat.driverId = selected.personalId;
            heartbeat.latitude = randomLat();
            heartbeat.longitude = randomLong();
            heartbeat.speed = randomSpeed();
            heartbeat.timestamp = Instant.now();

            heartbeatEmitter.send(heartbeat);
        } catch (Exception e) {
            LOG.error("Failed to fetch drivers or generate heartbeat", e);
        }
    }

    private double randomLat() {
        return 37.0 + random.nextDouble();
    }

    private double randomLong() {
        return -122.0 + random.nextDouble();
    }

    private double randomSpeed() {
        return 20 + random.nextDouble() * 60;
    }

}