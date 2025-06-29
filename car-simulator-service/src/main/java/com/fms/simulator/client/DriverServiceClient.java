package com.fms.simulator.client;

import com.fms.simulator.model.Driver;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/drivers")
@RegisterRestClient
public interface DriverServiceClient {

    @GET
    List<Driver> getAllDrivers();

}