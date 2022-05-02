package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;

@Path("/projekt")
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
    public String getProject(@QueryParam("id") String id) {
        if(id == null) {
            return jsonb.toJson(projekte);
        } else {
            return jsonb.toJson(projekte.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(new Projekt()));
        }
    }

}