package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Artefakt;
import de.fhbi.webbasedapps.projektsammlung.errors.Error400;
import de.fhbi.webbasedapps.projektsammlung.errors.Error404;
import de.fhbi.webbasedapps.projektsammlung.errors.Error500;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/artefakte")
public class EndpointArtefakte {

    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    @PersistenceContext(unitName = "ProjektsammlungPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response getArtefakte() {
        return Response.ok(jsonb.toJson(entityManager.createNamedQuery("Artefakt.findAll", Artefakt.class))).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getArtefakte(@PathParam("id") String id) {
        Artefakt a = entityManager.find(Artefakt.class, id);
        if(a == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            return Response.ok(jsonb.toJson(a)).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addArtefakt(Artefakt a) throws SystemException {
        if(a != null) {
            a.setId(UUID.randomUUID().toString());
            try {
                utx.begin();
                entityManager.persist(a);
                utx.commit();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error400.getInstance())).build();
        }
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response patchArtefakt(Artefakt artefakt, @PathParam("id") String id) throws SystemException {
        Artefakt artefaktToUpdate = entityManager.find(Artefakt.class, id);
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

            try {
                utx.begin();
                entityManager.merge(artefaktToUpdate);
                utx.commit();
                return Response.ok(jsonb.toJson(artefaktToUpdate)).build();
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
    public Response deleteArtefakt(@PathParam("id") String id) throws SystemException {
        Artefakt artefakt = entityManager.find(Artefakt.class, id);
        if(artefakt == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            try {
                utx.begin();
                entityManager.remove(entityManager.contains(artefakt) ? artefakt : entityManager.merge(artefakt));
                utx.commit();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }
            return Response.ok().build();
        }
    }

}
