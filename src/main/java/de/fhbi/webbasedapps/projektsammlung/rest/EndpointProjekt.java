package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;
import de.fhbi.webbasedapps.projektsammlung.errors.*;

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

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    public Response patchProject(Projekt projekt, @QueryParam("id") String id) {
        Projekt projektToUpdate = projekte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(projektToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            if(projekt == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
            }
            if(projekt.getProjektStart() != 0) projektToUpdate.setProjektStart(projekt.getProjektStart());
            if(projekt.getKurzbeschreibung() != null) projekt.setKurzbeschreibung(projekt.getKurzbeschreibung());
            if(projekt.getLogo() != null) projektToUpdate.setLogo(projekt.getLogo());
            if(projekt.getTitel() != null) projektToUpdate.setTitel(projekt.getTitel());
            return Response.ok(jsonb.toJson(projektToUpdate)).build();
        }
    }

    @DELETE
    @Produces("application/json")
    public Response deleteProject(@QueryParam("id") String id) {
        Projekt projektToDelete = projekte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(projektToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            projekte.remove(projektToDelete);
            return Response.noContent().build();
        }
    }
}