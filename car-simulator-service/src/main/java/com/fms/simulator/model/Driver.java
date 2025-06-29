package com.fms.simulator.model;

public class Driver {

    public Long personalId;

    public String name;

    public String phone;

    public String carPlateNumber;

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + personalId +
                ", name='" + name + '\'' +
                ", carPlateNumber='" + carPlateNumber + '\'' +
                '}';
    }
}