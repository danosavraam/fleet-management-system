package com.fms.fleet.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends PanacheEntityBase {

    @Id
    @Column(name = "plate_number")
    public String plateNumber;

    public String model;

    public String manufacturer;

    @OneToOne(mappedBy = "assignedCar", fetch = FetchType.LAZY)
    public Driver driver;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    public List<Trip> trips;

}