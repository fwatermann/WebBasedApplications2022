package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Artefakt;
import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/artefakte")
public class EndpointArtefakte {


    private static ArrayList<Artefakt> artefakte = new ArrayList<>();
    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

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
}
