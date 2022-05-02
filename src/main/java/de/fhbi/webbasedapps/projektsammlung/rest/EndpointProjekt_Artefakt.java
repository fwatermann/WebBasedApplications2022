package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;
import de.fhbi.webbasedapps.projektsammlung.classes.Projekt_Artefakt;
import de.fhbi.webbasedapps.projektsammlung.errors.Error400;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

@Path("/Projekt_Artefakte")
public class EndpointProjekt_Artefakt {
    private static ArrayList<Projekt_Artefakt> projektArtefakte = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    static {
        projektArtefakte.add(new Projekt_Artefakt("1","1",50.0));
        projektArtefakte.add(new Projekt_Artefakt("1","2",34.0));
        projektArtefakte.add(new Projekt_Artefakt("2","3",156.0));
    }

    @GET
    @Produces("application/json")
    public Response getProject_Artefact(@QueryParam("projektid") String projektId,@QueryParam("artefaktid")String artefaktId) {
        if(projektId == null || artefaktId == null) {
            return Response.ok(jsonb.toJson(projektArtefakte)).build();
        } else {
            Projekt_Artefakt projekt = projektArtefakte.stream().filter(pa -> pa.getProjektId().equals(projektId) && pa.getArtefaktId().equals(artefaktId)).findFirst().orElse(null);
            if(projektArtefakte == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
            } else {
                return Response.ok(jsonb.toJson(projektArtefakte)).build();
            }
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postProject_Artefact(Projekt_Artefakt projekt_artefakt) {
        if(projekt_artefakt != null) {
            projektArtefakte.add(projekt_artefakt);
            return Response.ok(jsonb.toJson(projekt_artefakt)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
        }
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    public Response patchProject_Artefact(Projekt_Artefakt projekt_artefakt, @QueryParam("projektid") String projektid,@QueryParam("artefactid") String artefactid) {
        Projekt_Artefakt projekt_artefaktToUpdate = projektArtefakte.stream().filter(pa -> pa.getProjektId().equals(projektid) && pa.getArtefaktId().equals(artefactid)).findFirst().orElse(null);
        if(projekt_artefaktToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            if(projekt_artefakt == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
            }
            if(projekt_artefakt.getArtefaktId() != null) projekt_artefaktToUpdate.setArtefaktId(projekt_artefakt.getArtefaktId());
            if(projekt_artefakt.getProjektId() != null) projekt_artefaktToUpdate.setProjektId(projekt_artefakt.getProjektId());
            return Response.ok(jsonb.toJson(projekt_artefaktToUpdate)).build();
        }
    }

    @DELETE
    public Response deleteProject_Artefact(@QueryParam("projektid") String projektid,@QueryParam("artefaktid") String artefaktid ) {
        Projekt_Artefakt projekt_artefaktToDelete = projektArtefakte.stream().filter(pa -> pa.getProjektId().equals(projektid) && pa.getArtefaktId().equals(artefaktid)).findFirst().orElse(null);
        if(projekt_artefaktToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            projektArtefakte.remove(projekt_artefaktToDelete);
            return Response.noContent().build();
        }
    }
}
