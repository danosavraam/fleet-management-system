package com.fms.penalty.service;

import com.fms.penalty.model.Heartbeat;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class HeartbeatConsumer {

    @Inject
    PenaltyService penaltyService;

    @Incoming("heartbeat-in")
    @Blocking
    public void consume(Heartbeat heartbeat) {
        penaltyService.processHeartbeat(heartbeat);
    }

}