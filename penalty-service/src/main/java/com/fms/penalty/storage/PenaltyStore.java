package com.fms.penalty.storage;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.string.StringCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PenaltyStore {

    private final StringCommands<String, String> redis;

    @Inject
    public PenaltyStore(RedisDataSource dataSource) {
        this.redis = dataSource.string(String.class);
    }

    public void addPoints(Long driverId, int points) {
        redis.incrby(String.valueOf(driverId), points);
    }

    public String getPoints(Long driverId) {
        return redis.get(String.valueOf(driverId));
    }

    public Map<String, String> getAll() {
        Map<String, String> all = new HashMap<>();

        var keysResponse = redis.getDataSource().execute("KEYS", "*");

        if (keysResponse != null && keysResponse.size() > 0) {
            for (int i = 0; i < keysResponse.size(); i++) {
                String key = keysResponse.get(i).toString();
                String val = redis.get(key);
                if (val != null) {
                    all.put(key, val);
                }
            }
        }

        return all;
    }

    public void reset(Long driverId) {
        redis.getDataSource().execute("DEL", String.valueOf(driverId));
    }
}