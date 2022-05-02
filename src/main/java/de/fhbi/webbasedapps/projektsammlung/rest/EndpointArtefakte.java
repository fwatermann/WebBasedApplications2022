package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Artefakt;
import de.fhbi.webbasedapps.projektsammlung.classes.Aufgabenbereich;
import de.fhbi.webbasedapps.projektsammlung.errors.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

@Path("/artefakte")
public class EndpointArtefakte {


    private static ArrayList<Artefakt> artefakte = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    static {
        artefakte.add(new Artefakt("1", "Artefakt 1", "Eine Kurzbeschreibung", new Aufgabenbereich(), 10));
        artefakte.add(new Artefakt("2", "Artefakt 2", "Eine Kurzbeschreibung",  null, 20));
        artefakte.add(new Artefakt("3", "Artefakt 3", "Eine Kurzbeschreibung",  null, 30));
    }

    @GET
    @Produces("application/json")
    public Response getArtefakte(@QueryParam("id") String id) {
        if(id == null) {
            return Response.ok(jsonb.toJson(artefakte)).build();
        } else {
            Artefakt a = artefakte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
            if(a == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
            } else {
                return Response.ok(jsonb.toJson(a)).build();
            }
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addArtefakt(Artefakt a) {
        if(a != null) {
            a.setId(UUID.randomUUID().toString());
            artefakte.add(a);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
        }
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    public Response patchArtefakt(Artefakt artefakt, @QueryParam("id") String id) {
        Artefakt artefaktToUpdate = artefakte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(artefaktToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            if(artefakt == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
            }

            if(artefakt.getTitel() != null) artefaktToUpdate.setTitel(artefakt.getTitel());
            if(artefakt.getKurzbeschreibung() != null) artefaktToUpdate.setKurzbeschreibung(artefakt.getKurzbeschreibung());
            if(artefakt.getAufgabenbereich() != null) artefaktToUpdate.setAufgabenbereich(artefakt.getAufgabenbereich());
            if(artefakt.getGeplanteArbeitszeit() != 0) artefaktToUpdate.setGeplanteArbeitszeit(artefakt.getGeplanteArbeitszeit());

            return Response.ok(jsonb.toJson(artefaktToUpdate)).build();
        }
    }

    @DELETE
    public Response deleteArtefakt(@QueryParam("id") String id) {
        Artefakt artefakt = artefakte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(artefakt == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            artefakte.remove(artefakt);
            return Response.ok().build();
        }
    }

}
