package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Aufgabenbereich;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.Jsonb;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

public class EndpointAufgabenbereich {
    private static ArrayList<Aufgabenbereich> aufgabenbereiche = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    static {
        aufgabenbereiche.add(new Aufgabenbereich("1","Aufgabenbereich 1", "Das ist der erste Aufgabenbereich"));
        aufgabenbereiche.add(new Aufgabenbereich("2","Aufgabenbereich 2", "Das ist der zweite Aufgabenbereich"));
        aufgabenbereiche.add(new Aufgabenbereich("1","Aufgabenbereich 1", "Das ist der dritte Aufgabenbereich"));
    }

    @GET
    @Produces("application/json")
    public Response getAufgabenbereich(@QueryParam("id") String id){
        if(id == null){
            return Response.ok(jsonb.toJson(aufgabenbereiche)).build();
        }else{
            Aufgabenbereich aufgabenbereich = aufgabenbereiche.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
            if(aufgabenbereich == null){
                return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
            }else{
                return Response.ok(jsonb.toJson(aufgabenbereich)).build();
            }
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postAufgabenbereich(Aufgabenbereich aufgabenbereich){
        aufgabenbereich.setId(UUID.randomUUID().toString());
        aufgabenbereiche.add(aufgabenbereich);
        return Response.ok(jsonb.toJson(aufgabenbereich)).build();
    }

    @PATCH
    @Consumes
    @Produces
    public Response patchAufgabenbereich(Aufgabenbereich aufgabenbereich,@QueryParam("id") String id){
        Aufgabenbereich aufgabenbereichToUpdate = aufgabenbereiche.stream().filter(a->a.getId().equals(id)).findFirst().orElse(null);
        if(aufgabenbereichToUpdate == null){
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        }else{
            if(aufgabenbereich == null){
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error404.getInstance())).build();
            }
            if(aufgabenbereich.getTitel() != null) aufgabenbereichToUpdate.setTitel(aufgabenbereich.getTitel());
            if(aufgabenbereich.getKurzbeschreibung() != null) aufgabenbereichToUpdate.setKurzbeschreibung(aufgabenbereich.getKurzbeschreibung());
            return Response.ok(jsonb.toJson(aufgabenbereichToUpdate)).build();
        }
    }

    @DELETE
    public Response deleteAufgabenbereich(@QueryParam("id") String id){
        Aufgabenbereich aufgabenbereichToDelete = aufgabenbereiche.stream().filter(a->a.getId().equals(id)).findFirst().orElse(null);
        if(aufgabenbereichToDelete == null){
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            aufgabenbereiche.remove(aufgabenbereichToDelete);
            return Response.noContent().build();
        }
    }
}
