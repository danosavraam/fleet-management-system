package com.fms.fleet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Digits;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "A registered driver in the fleet system")
public class DriverDto {

    @NotNull(message = "Personal ID is required")
    @Digits(integer = 10, fraction = 0, message = "ID must be up to 10 digits with no decimals")
    @Schema(description = "National ID of the driver (used as primary key)", examples = "1234567890")
    public Long personalId;

    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the driver", examples = "George Papadopoulos")
    public String name;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "Contact phone number", examples = "+35799123456")
    public String phone;

    @Schema(description = "Plate number of the assigned car", examples = "ABC-1234")
    public String carPlateNumber;

}