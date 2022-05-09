package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt_Aufgabenbereich;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;
import de.fhbi.webbasedapps.projektsammlung.errors.Error500;
import de.fhbi.webbasedapps.projektsammlung.keys.PK_Projekt_Aufgabenbereich;

import javax.annotation.Resource;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/projekte/{projektId}/aufgabenbereiche")

public class EndpointProjekt_Aufgabenbereich {


    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    @PersistenceContext(name = "ProjektSammlungPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response getProject_Aufgabenbereiche(@PathParam("projektId") String projektId){
        Collection<Projekt_Aufgabenbereich> projekt_aufgabenbereiche = em.createNamedQuery("Projekt_Aufgabenbereich.findAll",Projekt_Aufgabenbereich.class).setParameter("projektId", projektId).getResultList();
        return Response.ok(jsonb.toJson(projekt_aufgabenbereiche)).build();
    }

    @POST
    @Produces("application/json")
    @Path("/{aufgabenbereichId}")
    public Response postProject_Aufgabenbereich(@PathParam("projektId") String projektId, @PathParam("aufgabenbereichId") String aufgabenbereichId) throws SystemException {
        Projekt_Aufgabenbereich projekt_aufgabenbereich = new Projekt_Aufgabenbereich();
        projekt_aufgabenbereich.setProjektId(projektId);
        projekt_aufgabenbereich.setAufgabenbereichId(aufgabenbereichId);
        try{
            utx.begin();
            em.persist(projekt_aufgabenbereich);
            utx.commit();
        } catch(Exception e){
            if(utx.getStatus() == Status.STATUS_ACTIVE) utx.rollback();
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
        }
        return Response.ok(jsonb.toJson(projekt_aufgabenbereich)).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("/{aufgabenbereichId}")
    public Response deleteProject_Aufgabenbereich(@PathParam("projektId") String projektId, @PathParam("aufgabenbereichId") String aufgabenbereichId) throws SystemException {
        Projekt_Aufgabenbereich projekt_aufgabenbereichToDelete = em.find(Projekt_Aufgabenbereich.class, new PK_Projekt_Aufgabenbereich(projektId, aufgabenbereichId));
        if(projekt_aufgabenbereichToDelete == null){
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        }else {
            try{
                utx.begin();
                em.remove(projekt_aufgabenbereichToDelete);
                utx.commit();
            } catch(Exception e){
                if(utx.getStatus() == Status.STATUS_ACTIVE) utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
            return Response.noContent().build();
        }
    }

}
