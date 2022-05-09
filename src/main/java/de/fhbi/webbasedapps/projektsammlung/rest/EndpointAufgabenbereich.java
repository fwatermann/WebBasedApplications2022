package de.fhbi.webbasedapps.projektsammlung.rest;

import de.fhbi.webbasedapps.projektsammlung.classes.Aufgabenbereich;
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
@Path("/aufgabenbereiche")
public class EndpointAufgabenbereich {

    private static Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withNullValues(true));

    @PersistenceContext(name = "ProjektsammlungPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response getAllAufgabenbereiche() {
        return Response.ok(jsonb.toJson(em.createNamedQuery("Aufgabenbereich.findAll", Aufgabenbereich.class))).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getAufgabenbereich(@PathParam("id") String id) {
        Aufgabenbereich aufgabenbereich = em.find(Aufgabenbereich.class, id);
        if (aufgabenbereich == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            return Response.ok(jsonb.toJson(aufgabenbereich)).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postAufgabenbereich(Aufgabenbereich aufgabenbereich) throws SystemException {
        aufgabenbereich.setId(UUID.randomUUID().toString());
        try {
            utx.begin();
            em.persist(aufgabenbereich);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
        }
        return Response.ok(jsonb.toJson(aufgabenbereich)).build();
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response patchAufgabenbereich(Aufgabenbereich aufgabenbereich, @PathParam("id") String id) throws SystemException {
        Aufgabenbereich aufgabenbereichToUpdate = em.find(Aufgabenbereich.class, id);
        if (aufgabenbereichToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {
            if (aufgabenbereich == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson(Error404.getInstance())).build();
            }

            if (aufgabenbereich.getTitel() != null) aufgabenbereichToUpdate.setTitel(aufgabenbereich.getTitel());
            if (aufgabenbereich.getKurzbeschreibung() != null) aufgabenbereichToUpdate.setKurzbeschreibung(aufgabenbereich.getKurzbeschreibung());

            try {
                utx.begin();
                em.merge(aufgabenbereichToUpdate);
                utx.commit();
            } catch(Exception e) {
                utx.rollback();
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonb.toJson(Error500.getInstance())).build();
            }

            return Response.ok(jsonb.toJson(aufgabenbereichToUpdate)).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public Response deleteAufgabenbereich(@PathParam("id") String id) throws SystemException {
        Aufgabenbereich aufgabenbereichToDelete = em.find(Aufgabenbereich.class, id);
        if (aufgabenbereichToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(jsonb.toJson(Error404.getInstance())).build();
        } else {

            try {
                utx.begin();
                em.remove(em.contains(aufgabenbereichToDelete) ? aufgabenbereichToDelete : em.merge(aufgabenbereichToDelete));
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
