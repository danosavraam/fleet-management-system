package com.fms.fleet.mapper;

import com.fms.fleet.domain.Car;
import com.fms.fleet.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CarMapper {

    CarDto toDto(Car entity);

    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "trips", ignore = true)
    Car toEntity(CarDto carDto);

    List<CarDto> toDtoList(List<Car> cars);

}