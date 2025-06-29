package com.fms.simulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Heartbeat {

    public String carId;

    public double latitude;

    public double longitude;

    public double speed;

    public Long driverId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Instant timestamp;

}