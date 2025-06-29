package com.fms.penalty.service;

import com.fms.penalty.model.Heartbeat;
import com.fms.penalty.storage.PenaltyStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PenaltyService {

    @Inject
    PenaltyStore store;

    public void processHeartbeat(Heartbeat heartbeat) {
        int penalty = calculatePenalty(heartbeat.speed);
        if (penalty > 0) {
            store.addPoints(heartbeat.driverId, penalty);
        }
    }

    int calculatePenalty(double speed) {
        if (speed > 80) return 5;
        if (speed > 60) return 2;
        return 0;
    }

}