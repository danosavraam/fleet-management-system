package com.fms.fleet.service;

import com.fms.fleet.exception.BadRequestException;
import com.fms.fleet.exception.ConflictException;
import com.fms.fleet.domain.Driver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class DriverService {

    public List<Driver> getAll() {
        List<Driver> drivers = Driver.listAll();
        if (drivers == null) {
            throw new NotFoundException("Drivers not found");
        }
        return drivers;
    }

    public Driver getById(Long id) {
        Driver driver = Driver.findById(id);
        if (driver == null) {
            throw new NotFoundException("Driver not found");
        }
        return driver;
    }

    @Transactional
    public void create(Driver driver) {
        if (Driver.findById(driver.personalId) != null) {
            throw new ConflictException("Driver with this ID already exists");
        }
        driver.persist();
    }

    @Transactional
    public Driver update(Long id, Driver updated) {
        Driver existing = getById(id);

        if (!existing.personalId.equals(updated.personalId)) {
            throw new BadRequestException("Changing personalId is not allowed");
        }

        existing.name = updated.name;
        existing.phone = updated.phone;
        existing.assignedCar = updated.assignedCar;

        return existing;
    }

    @Transactional
    public void delete(Long id) {
        boolean deleted = Driver.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Driver not found");
        }
    }

}