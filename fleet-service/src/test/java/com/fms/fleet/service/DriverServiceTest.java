package com.fms.fleet.service;

import com.fms.fleet.PostgresTestResource;
import com.fms.fleet.domain.Driver;
import com.fms.fleet.dto.DriverDto;
import com.fms.fleet.mapper.DriverMapper;
import io.quarkus.test.common.QuarkusTestResource;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
class DriverServiceTest {

    @Inject
    DriverService driverService;

    @Inject
    DriverMapper driverMapper;

    @Test
    void testAddAndGetDriver() {
        DriverDto driverDto = new DriverDto();
        driverDto.setPersonalId(1234567890L);
        driverDto.setName("George Papadopoulos");
        driverDto.setPhone("+35799123456");
        driverDto.setCarPlateNumber("ABC-1234");

        driverService.create(driverMapper.toEntity(driverDto));

        List<Driver> allDrivers = driverService.getAll();
        assertTrue(allDrivers.stream().anyMatch(d -> "George Papadopoulos".equals(d.getName())));
    }

}