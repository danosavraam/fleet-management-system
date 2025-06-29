package com.fms.fleet.service;

import com.fms.fleet.domain.Trip;
import com.fms.fleet.exception.BadRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class TripService {

    public List<Trip> getAll() {
        List<Trip> trips = Trip.listAll();
        if (trips == null) {
            throw new NotFoundException("Trips not found");
        }
        return trips;
    }

    public Trip getById(Long id) {
        Trip trip = Trip.findById(id);
        if (trip == null) {
            throw new NotFoundException("Trip not found");
        }
        return trip;
    }

    public List<Trip> getByDriverId(Long driverId) {
        return Trip.list("driver_id", driverId);
    }

    public List<Trip> getByCarId(Long carId) {
        return Trip.list("car_id", carId);
    }

    @Transactional
    public void create(Trip trip) {
        trip.persist();
    }

    @Transactional
    public Trip update(Long id, Trip updated) {
        Trip existing = getById(id);
        if (!existing.id.equals(updated.id)) {
            throw new BadRequestException("Changing trip id is not allowed");
        }
        existing.startTime = updated.startTime;
        existing.endTime = updated.endTime;
        existing.distanceKm = updated.distanceKm;
        return existing;
    }

    @Transactional
    public void delete(Long id) {
        Trip trip = getById(id);
        trip.delete();
    }

}