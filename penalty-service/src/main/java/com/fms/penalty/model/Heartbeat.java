package com.fms.penalty.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Heartbeat {

    public String carId;

    public Long driverId;

    public double latitude;

    public double longitude;

    public double speed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Instant timestamp;

}