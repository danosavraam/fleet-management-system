package com.fms.fleet.dto;

import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Car details")
public class CarDto {

    @NotBlank(message = "Plate number is required")
    @Schema(description = "License plate number", examples = "ABC-1234")
    public String plateNumber;

    @NotBlank(message = "Car model is required")
    @Schema(description = "Car model", examples = "Model S")
    public String model;

    @NotBlank(message = "Car manufacturer is required")
    @Schema(description = "Car manufacturer", examples = "Tesla")
    public String manufacturer;

}