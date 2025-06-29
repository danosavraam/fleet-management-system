package com.fms.fleet.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver extends PanacheEntityBase {

    @Id
    @Column(name = "personal_id")
    public Long personalId;

    public String name;

    public String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    public Car assignedCar;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    public List<Trip> trips;

}