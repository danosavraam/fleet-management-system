package com.fms.simulator.service;

import com.fms.simulator.KafkaTestResource;
import com.fms.simulator.model.Heartbeat;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(KafkaTestResource.class)
class HeartbeatServiceTest {

    @Inject
    HeartbeatService heartbeatService;

    @Test
    void testSendAndGetHeartbeat() {
        Heartbeat heartbeat = new Heartbeat();
        heartbeat.carId = "CAR123";
        heartbeat.driverId = 123456789L;
        heartbeat.latitude = 40.7128;
        heartbeat.longitude = -74.0060;
        heartbeat.speed = 70.0;
        heartbeat.timestamp = java.time.Instant.now();

        heartbeatService.sendHeartbeat(heartbeat);

        Heartbeat result = heartbeatService.getCurrentHeartbeat();
        assertNotNull(result);
        assertEquals("CAR123", result.carId);
    }

}