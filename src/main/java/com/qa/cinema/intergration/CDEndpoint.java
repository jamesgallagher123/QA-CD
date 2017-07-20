package com.qa.cinema.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.cinema.business.CDService;

@Path("/cinema")
public class CDEndpoint {

    @Inject
    private CDService service;

    @Path("/json")
    @GET
    @Produces({ "application/json" })
    public String getAllMovies() {
        return service.getAllCDs();
    }

    @Path("/json")
    @POST
    @Produces({ "application/json" })
    public String addMovie(String cd) {
        return service.createCD(cd);
    }

    @Path("/json/{id}")
    @PUT
    @Produces({ "application/json" })
    public String updateMovie(@PathParam("id") Long id, String cd) {
        return service.updateCD(id, cd);
    }

    @Path("/json/{id}")
    @DELETE
    @Produces({ "application/json" })
    public String deleteMovie(@PathParam("id") Long id) {
        return service.deleteCD(id);

    }
}