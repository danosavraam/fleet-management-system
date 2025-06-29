package com.fms.fleet.service;

import com.fms.fleet.domain.Car;
import com.fms.fleet.exception.BadRequestException;
import com.fms.fleet.exception.ConflictException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class CarService {

    public List<Car> getAll() {
        List<Car> cars = Car.listAll();
        if (cars == null) {
            throw new NotFoundException("Cars not found");
        }
        return cars;
    }

    public Car getByPlate(String plateNumber) {
        Car car = Car.findById(plateNumber);
        if (car == null) {
            throw new NotFoundException("Car not found");
        }
        return car;
    }

    @Transactional
    public void create(Car car) {
        if (Car.findById(car.plateNumber) != null) {
            throw new ConflictException("Car with this plate number already exists");
        }
        car.persist();
    }

    @Transactional
    public Car update(String plateNumber, Car updated) {
        Car existing = getByPlate(plateNumber);
        if (!existing.plateNumber.equals(updated.plateNumber)) {
            throw new BadRequestException("Changing plateNumber is not allowed");
        }
        existing.model = updated.model;
        existing.manufacturer = updated.manufacturer;
        return existing;
    }

    @Transactional
    public void delete(String plateNumber) {
        Car car = getByPlate(plateNumber);
        car.delete();
    }

}