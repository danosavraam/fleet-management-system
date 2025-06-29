package com.fms.fleet.controller;

import com.fms.fleet.domain.Car;
import com.fms.fleet.domain.Driver;
import com.fms.fleet.dto.DriverDto;
import com.fms.fleet.mapper.DriverMapper;
import com.fms.fleet.service.DriverService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverController {

    @Inject
    DriverService driverService;

    @Inject
    DriverMapper driverMapper;

    @GET
    @Operation(summary = "Retrieve all drivers")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Drivers found"),
            @APIResponse(responseCode = "404", description = "Drivers not found")
    })
    public List<DriverDto> getAll() {
        return driverMapper.toDtoList(driverService.getAll());
    }

    @GET
    @Path("/{personalId}")
    @Operation(summary = "Retrieve a driver by their national ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Driver found"),
            @APIResponse(responseCode = "404", description = "Driver not found")
    })
    public DriverDto getById(@PathParam("personalId") Long personalId) {
        return driverMapper.toDto(driverService.getById(personalId));
    }

    @POST
    @Operation(summary = "Create a new driver")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Driver created"),
            @APIResponse(responseCode = "409", description = "Driver with this ID already exists"),
            @APIResponse(responseCode = "404", description = "Car not found")
    })
    public Response create(
            @RequestBody(description = "Driver to create")
            @Valid DriverDto driverDto
    ) {
        Driver driver = driverMapper.toEntity(driverDto);

        if (driverDto.carPlateNumber != null && !driverDto.carPlateNumber.isBlank()) {
            Car car = Car.findById(driverDto.carPlateNumber);
            if (car == null) {
                throw new NotFoundException("Car with plate number '" + driverDto.carPlateNumber + "' not found");
            }
            driver.assignedCar = car;
        }

        driverService.create(driver);

        return Response.status(Response.Status.CREATED).entity(driverDto).build();
    }

    @PUT
    @Path("/{personalId}")
    @Operation(summary = "Update an existing driver")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Driver updated"),
            @APIResponse(responseCode = "400", description = "Changing personalId is not allowed"),
            @APIResponse(responseCode = "404", description = "Driver not found")
    })
    public DriverDto update(
            @PathParam("personalId") Long personalId,
            @RequestBody(description = "Updated driver details")
            @Valid DriverDto driverDto
    ) {
        Driver driver = driverMapper.toEntity(driverDto);

        if (driverDto.carPlateNumber != null && !driverDto.carPlateNumber.isBlank()) {
            Car car = Car.findById(driverDto.carPlateNumber);
            if (car == null) {
                throw new NotFoundException("Car with plate number '" + driverDto.carPlateNumber + "' not found");
            }
            driver.assignedCar = car;
        }

        return driverMapper.toDto(driverService.update(personalId, driver));
    }

    @DELETE
    @Path("/{personalId}")
    @Operation(summary = "Delete a driver by ID")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Driver deleted"),
            @APIResponse(responseCode = "404", description = "Driver not found")
    })
    public void delete(@PathParam("personalId") Long personalId) {
        driverService.delete(personalId);
    }

}