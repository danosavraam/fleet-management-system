package com.fms.fleet.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", ex.getMessage()))
                .build();
    }

}