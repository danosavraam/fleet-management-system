package com.fms.fleet.mapper;

import com.fms.fleet.dto.DriverDto;
import com.fms.fleet.domain.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DriverMapper {

    @Mapping(target = "carPlateNumber", source = "assignedCar.plateNumber")
    DriverDto toDto(Driver entity);

    @Mapping(target = "assignedCar", ignore = true)
    @Mapping(target = "trips", ignore = true)
    Driver toEntity(DriverDto driverDto);

    List<DriverDto> toDtoList(List<Driver> drivers);

}