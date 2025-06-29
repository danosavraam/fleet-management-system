package com.fms.penalty.controller;

import com.fms.penalty.storage.PenaltyStore;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/penalties")
@Produces(MediaType.APPLICATION_JSON)
public class PenaltyController {

    @Inject
    PenaltyStore store;

    @GET
    public Map<String, String> getAll() {
        return store.getAll();
    }

    @GET
    @Path("/{driverId}")
    public String getPoints(@PathParam("driverId") Long driverId) {
        return store.getPoints(driverId);
    }

    @DELETE
    @Path("/{driverId}")
    public void reset(@PathParam("driverId") Long driverId) {
        store.reset(driverId);
    }

}