package com.fms.fleet.controller;

import com.fms.fleet.domain.Car;
import com.fms.fleet.domain.Driver;
import com.fms.fleet.domain.Trip;
import com.fms.fleet.dto.CreateTripDto;
import com.fms.fleet.dto.TripDto;
import com.fms.fleet.mapper.TripMapper;
import com.fms.fleet.service.TripService;
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

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripController {

    @Inject
    TripService tripService;

    @Inject
    TripMapper tripMapper;

    @GET
    @Operation(summary = "Retrieve all trips")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Trips found"),
            @APIResponse(responseCode = "404", description = "Trips not found")
    })
    public List<TripDto> getAll() {
        return tripMapper.toDtoList(tripService.getAll());
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Retrieve a trip by ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Trip found"),
            @APIResponse(responseCode = "404", description = "Trip not found")
    })
    public TripDto getById(@PathParam("id") Long id) {
        return tripMapper.toDto(tripService.getById(id));
    }

    @GET
    @Path("/{driverId}")
    @Operation(summary = "Retrieve trips by driver id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Trips found"),
            @APIResponse(responseCode = "404", description = "Trips not found")
    })
    public List<TripDto> getByDriverId(@PathParam("driverId") Long driverId) {
        return tripMapper.toDtoList(tripService.getByDriverId(driverId));
    }

    @GET
    @Path("/{carId}")
    @Operation(summary = "Retrieve trips by car id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Trips found"),
            @APIResponse(responseCode = "404", description = "Trips not found")
    })
    public List<TripDto> getByCarId(@PathParam("carId") Long carId) {
        return tripMapper.toDtoList(tripService.getByCarId(carId));
    }

    @POST
    @Operation(summary = "Create a new trip")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Trip created"),
            @APIResponse(responseCode = "404", description = "Driver or Car not found")
    })
    public Response create(
            @RequestBody(description = "Trip to create")
            @Valid CreateTripDto createTripDto
    ) {
        Trip trip = tripMapper.fromCreateDto(createTripDto);

        Driver driver = Driver.findById(createTripDto.driverId);
        if (driver == null) throw new NotFoundException("Driver not found");
        trip.driver = driver;

        Car car = Car.findById(createTripDto.carId);
        if (car == null) throw new NotFoundException("Car not found");
        trip.car = car;

        tripService.create(trip);
        return Response.status(Response.Status.CREATED).entity(tripMapper.toDto(trip)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update an existing trip")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Trip updated"),
            @APIResponse(responseCode = "400", description = "Changing trip id is not allowed"),
            @APIResponse(responseCode = "404", description = "Trip not found")
    })
    public TripDto update(
            @PathParam("id") Long id,
            @RequestBody(description = "Updated trip details")
            @Valid TripDto tripDto
    ) {
        Trip trip = tripMapper.toEntity(tripDto);

        Driver driver = Driver.findById(tripDto.driverId);
        if (driver == null) throw new NotFoundException("Driver not found");
        trip.driver = driver;

        Car car = Car.findById(tripDto.carId);
        if (car == null) throw new NotFoundException("Car not found");
        trip.car = car;

        return tripMapper.toDto(tripService.update(id, trip));
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a trip by ID")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Trip deleted"),
            @APIResponse(responseCode = "404", description = "Trip not found")
    })
    public void delete(@PathParam("id") Long id) {
        tripService.delete(id);
    }

}