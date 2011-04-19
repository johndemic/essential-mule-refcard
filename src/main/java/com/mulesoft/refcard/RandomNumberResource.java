package com.mulesoft.refcard;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/random-number")
public class RandomNumberResource {

    @GET
    @Produces("text/plain")
    public String getRandomNumber() {
        return Double.toString(Math.random());

    }
}
