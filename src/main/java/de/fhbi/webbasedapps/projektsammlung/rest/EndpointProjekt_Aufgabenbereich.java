package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt_Artefakt;
import de.fhbi.webbasedapps.projektsammlung.classes.Projekt_Aufgabenbereich;
import de.fhbi.webbasedapps.projektsammlung.errors.Error400;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("/projekt_aufgabenbereich")

public class EndpointProjekt_Aufgabenbereich {

    private static ArrayList<Projekt_Aufgabenbereich> projektAufgabenbereichRefs = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    static {
        projektAufgabenbereichRefs.add(new Projekt_Aufgabenbereich("1", "1"));
        projektAufgabenbereichRefs.add(new Projekt_Aufgabenbereich("1", "2"));
        projektAufgabenbereichRefs.add(new Projekt_Aufgabenbereich("2", "3"));
    }

    @GET
    @Produces("application/json")
    public Response getProject_Artefact(@QueryParam("projektId") String projektId, @QueryParam("aufgabenbereichId") String aufgabenbereichId) {
        if (aufgabenbereichId == null && projektId == null) {
            return Response.ok(jsonb.toJson(projektAufgabenbereichRefs)).build();
        }
        if(aufgabenbereichId != null && projektId != null){
            Projekt_Aufgabenbereich projekt_aufgabenbereich = projektAufgabenbereichRefs.stream().filter(pa -> pa.getProjektId().equals(projektId) && pa.getAufgabenbereichId().equals(aufgabenbereichId)).findFirst().orElse(null);
            if (projekt_aufgabenbereich == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
            } else {
                return Response.ok(jsonb.toJson(projekt_aufgabenbereich)).build();
            }
        }
        if (projektId == null) {
            return Response.ok(jsonb.toJson(projektAufgabenbereichRefs.stream().filter(pa -> pa.getAufgabenbereichId().equals(aufgabenbereichId)).collect(Collectors.toList()))).build();
        }
        return Response.ok(jsonb.toJson(projektAufgabenbereichRefs.stream().filter(pa -> pa.getProjektId().equals(projektId)).collect(Collectors.toList()))).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postProject_Artefact(Projekt_Aufgabenbereich projekt_aufgabenbereich) {
        if (projekt_aufgabenbereich != null) {
            projektAufgabenbereichRefs.add(projekt_aufgabenbereich);
            return Response.ok(jsonb.toJson(projekt_aufgabenbereich)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
        }
    }

    @DELETE
    @Produces("application/json")
    public Response deleteProject_Artefact(@QueryParam("projektId") String projektid, @QueryParam("aufgabenbereichId") String aufgabenbereichId) {
        Projekt_Aufgabenbereich projekt_aufgabenbereich = projektAufgabenbereichRefs.stream().filter(pa -> pa.getProjektId().equals(projektid) && pa.getAufgabenbereichId().equals(aufgabenbereichId)).findFirst().orElse(null);
        if (projekt_aufgabenbereich == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            projektAufgabenbereichRefs.remove(projekt_aufgabenbereich);
            return Response.noContent().build();
        }
    }
}
