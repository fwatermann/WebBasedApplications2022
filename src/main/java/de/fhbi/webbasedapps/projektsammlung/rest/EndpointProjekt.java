package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

@Path("/projekte")
public class EndpointProjekt {

    private static ArrayList<Projekt> projekte = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    static {
        projekte.add(new Projekt("1", "Projekt 1", "Das ist das erste Projekt", "/images/projekt1.jpg", 0));
        projekte.add(new Projekt("2", "Projekt 2", "Das ist das zweite Projekt", "/images/projekt2.jpg", 0));
        projekte.add(new Projekt("3", "Projekt 3", "Das ist das dritte Projekt", "/images/projekt3.jpg", 0));
    }

    @GET
    @Produces("application/json")
    public Response getProject(@QueryParam("id") String id) {
        if(id == null) {
            return Response.ok(jsonb.toJson(projekte)).build();
        } else {
            Projekt projekt = projekte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
            if(projekt == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
            } else {
                return Response.ok(jsonb.toJson(projekt)).build();
            }
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postProject(Projekt projekt) {
        projekt.setId(UUID.randomUUID().toString());
        projekte.add(projekt);
        return Response.ok(jsonb.toJson(projekt)).build();
    }

}