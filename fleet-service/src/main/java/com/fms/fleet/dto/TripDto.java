package com.fms.fleet.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Trip details")
public class TripDto {

    @Schema(description = "Trip ID", examples = "1")
    public Long id;

    @NotNull(message = "Driver ID is required")
    @Digits(integer = 10, fraction = 0, message = "ID must be up to 10 digits with no decimals")
    @Schema(description = "Driver ID", examples = "101")
    public Long driverId;

    @NotNull(message = "Car ID is required")
    @Schema(description = "Plate number of the car", examples = "ABC-1234")
    public String carId;

    @NotNull(message = "Trip start time is required")
    @Schema(description = "Trip start time", examples = "2025-06-28T10:00:00")
    public LocalDateTime startTime;

    @NotNull(message = "Trip end time is required")
    @Schema(description = "Trip end time", examples = "2025-06-28T11:00:00")
    public LocalDateTime endTime;

    @NotNull(message = "Trip distance is required")
    @Schema(description = "Trip distance in kilometers", examples = "15.75")
    public double distanceKm;

}