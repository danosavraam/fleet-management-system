package com.fms.fleet.mapper;

import com.fms.fleet.domain.Trip;
import com.fms.fleet.dto.CreateTripDto;
import com.fms.fleet.dto.TripDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TripMapper {

    @Mapping(target = "driverId", source = "driver.personalId")
    @Mapping(target = "carId", source = "car.plateNumber")
    TripDto toDto(Trip trip);

    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "car", ignore = true)
    Trip toEntity(TripDto tripDto);

    List<TripDto> toDtoList(List<Trip> trips);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "car", ignore = true)
    Trip fromCreateDto(CreateTripDto createTripDto);

}