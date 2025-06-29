package com.fms.penalty.service;

import com.fms.penalty.model.Heartbeat;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PenaltyServiceTest {

    @Inject
    PenaltyService penaltyService;

    @Test
    void testPenaltyCalculation() {
        Heartbeat heartbeat = new Heartbeat();
        heartbeat.driverId = 101L;
        heartbeat.speed = 85.0;
        heartbeat.timestamp = Instant.now();

        int penalty = penaltyService.calculatePenalty(heartbeat.speed);
        assertEquals(5, penalty);
    }

}