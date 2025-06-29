package com.fms.fleet.controller;

import com.fms.fleet.dto.CarDto;
import com.fms.fleet.mapper.CarMapper;
import com.fms.fleet.service.CarService;
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

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarController {

    @Inject
    CarService carService;

    @Inject
    CarMapper carMapper;

    @GET
    @Operation(summary = "Retrieve all cars")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Cars found"),
            @APIResponse(responseCode = "404", description = "Cars not found")
    })
    public List<CarDto> getAll() {
        return carMapper.toDtoList(carService.getAll());
    }

    @GET
    @Path("/{plateNumber}")
    @Operation(summary = "Retrieve a car by plate number")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Car found"),
            @APIResponse(responseCode = "404", description = "Car not found")
    })
    public CarDto getByPlate(@PathParam("plateNumber") String plateNumber) {
        return carMapper.toDto(carService.getByPlate(plateNumber));
    }

    @POST
    @Operation(summary = "Create a new car")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Car created"),
            @APIResponse(responseCode = "409", description = "Car with this plate number already exists")
    })
    public Response create(
            @RequestBody(description = "Car to create")
            @Valid CarDto carDto
    ) {
        carService.create(carMapper.toEntity(carDto));
        return Response.status(Response.Status.CREATED).entity(carDto).build();
    }

    @PUT
    @Path("/{plateNumber}")
    @Operation(summary = "Update an existing car")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Car updated"),
            @APIResponse(responseCode = "400", description = "Changing plate number is not allowed"),
            @APIResponse(responseCode = "404", description = "Car not found")
    })
    public CarDto update(
            @PathParam("plateNumber") String plateNumber,
            @RequestBody(description = "Updated car details")
            @Valid CarDto carDto) {
        return carMapper.toDto(carService.update(plateNumber, carMapper.toEntity(carDto)));
    }

    @DELETE
    @Path("/{plateNumber}")
    @Operation(summary = "Delete a car by plate number")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Car deleted"),
            @APIResponse(responseCode = "404", description = "Car not found")
    })
    public void delete(@PathParam("plateNumber") String plateNumber) {
        carService.delete(plateNumber);
    }

}