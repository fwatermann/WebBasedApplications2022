package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Projekt;
import de.fhbi.webbasedapps.projektsammlung.errors.*;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/projekte")
public class EndpointProjekt {

    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    @PersistenceContext(unitName = "ProjektsammlungPU")
    public EntityManager entityManager;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response getProjekte() {
        return Response.ok(jsonb.toJson(entityManager.createNamedQuery("Projekt.findAll", Projekt.class).getResultList())).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getProject(@PathParam("id") String id) {
        Projekt projekt = entityManager.find(Projekt.class, id);
        if(projekt == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            return Response.ok(jsonb.toJson(projekt)).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postProject(Projekt projekt) throws SystemException {
        if(projekt != null) {
            projekt.setId(UUID.randomUUID().toString());
            try {
                utx.begin();
                entityManager.persist(projekt);
                utx.commit();
                return Response.ok(jsonb.toJson(projekt)).build();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
        }
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response patchProject(Projekt projekt, @PathParam("id") String id) throws SystemException {
        Projekt projektToUpdate = entityManager.find(Projekt.class, id);
        if(projektToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            if(projekt == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
            }

            if(projekt.getProjektStart() != 0) projektToUpdate.setProjektStart(projekt.getProjektStart());
            if(projekt.getKurzbeschreibung() != null) projektToUpdate.setKurzbeschreibung(projekt.getKurzbeschreibung());
            if(projekt.getLogo() != null) projektToUpdate.setLogo(projekt.getLogo());
            if(projekt.getTitel() != null) projektToUpdate.setTitel(projekt.getTitel());

            try {
                utx.begin();
                entityManager.merge(projektToUpdate);
                utx.commit();
                return Response.ok(jsonb.toJson(projektToUpdate)).build();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
        }
    }

    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public Response deleteProject(@PathParam("id") String id)  throws SystemException {
        Projekt projektToDelete = entityManager.find(Projekt.class, id);
        if(projektToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            try {
                utx.begin();
                entityManager.remove(entityManager.contains(projektToDelete) ? projektToDelete : entityManager.merge(projektToDelete));
                utx.commit();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
            return Response.noContent().build();
        }
    }
}