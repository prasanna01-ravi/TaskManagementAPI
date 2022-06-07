package com.example.main.resources;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value="/hello")
@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "getGreeting", notes = "Returns hello", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Hello world!")})
    public String getGreeting(){
        return "Hello world!";
    }
}
