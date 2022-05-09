package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt_Artefakt;
import de.fhbi.webbasedapps.projektsammlung.keys.PK_Projekt_Artefakt;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;
import de.fhbi.webbasedapps.projektsammlung.errors.Error500;

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

@Path("/projekte/{projektId}/artefakte")
public class EndpointProjekt_Artefakt {

    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    @PersistenceContext(name = "ProjektSammlungPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response getProject_Artefact(@PathParam("projektId") String projektId) {
        Collection<Projekt_Artefakt> projekt_artefakte = em.createNamedQuery("Projekt_Artefakt.findAll", Projekt_Artefakt.class).setParameter("projektId", projektId).getResultList();
        return Response.ok(jsonb.toJson(projekt_artefakte)).build();
    }

    @POST
    @Produces("application/json")
    @Path("/{artefaktId}")
    public Response postProject_Artefact(@PathParam("projektId") String projektId, @PathParam("artefaktId") String artefactId) throws SystemException {
        Projekt_Artefakt projekt_artefakt = new Projekt_Artefakt();
        projekt_artefakt.setProjekt_id(projektId);
        projekt_artefakt.setArtefakt_id(artefactId);
        try {
            utx.begin();
            em.persist(projekt_artefakt);
            utx.commit();
        } catch(Exception e) {
            if(utx.getStatus() == Status.STATUS_ACTIVE) utx.rollback();
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
        }
        return Response.ok(jsonb.toJson(projekt_artefakt)).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("/{artefaktId}")
    public Response deleteProject_Artefact(@PathParam("projektId") String projektId, @PathParam("artefaktId") String artefactId) throws SystemException {
        Projekt_Artefakt projekt_artefaktToDelete = em.find(Projekt_Artefakt.class, new PK_Projekt_Artefakt(projektId, artefactId));
        if (projekt_artefaktToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            try {
                utx.begin();
                em.remove(projekt_artefaktToDelete);
                utx.commit();
            } catch(Exception e) {
                if(utx.getStatus() == Status.STATUS_ACTIVE) utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
            return Response.noContent().build();
        }
    }
}
