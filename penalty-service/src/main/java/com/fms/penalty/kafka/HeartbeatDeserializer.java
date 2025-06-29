package com.fms.penalty.kafka;

import com.fms.penalty.model.Heartbeat;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class HeartbeatDeserializer extends ObjectMapperDeserializer<Heartbeat> {

    public HeartbeatDeserializer() {
        super(Heartbeat.class);
    }

}