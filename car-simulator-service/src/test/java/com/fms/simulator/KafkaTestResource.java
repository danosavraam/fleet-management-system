package com.fms.simulator;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class KafkaTestResource implements QuarkusTestResourceLifecycleManager {

    private static final DockerImageName KAFKA_IMAGE = DockerImageName
            .parse("confluentinc/cp-kafka:7.6.1")
            .asCompatibleSubstituteFor("confluentinc/cp-kafka");

    private KafkaContainer kafka;

    @Override
    public Map<String, String> start() {
        kafka = new KafkaContainer(KAFKA_IMAGE);
        kafka.start();

        return Map.of(
                "kafka.bootstrap.servers", kafka.getBootstrapServers()
        );
    }

    @Override
    public void stop() {
        if (kafka != null) {
            kafka.stop();
        }
    }

}