package org.example;

import Data.Dueout;
import Data.Order;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Traced
@Timed

@Path("/microservice/dueout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DueoutMicroservice {
    @Inject
    DueoutDao dueoutDao;

    @POST
    @Path("createDueout")
    public Dueout createDueout(Order order) {
        Dueout dueout = new Dueout();
        dueout.order = order;
        dueout.lineItem = order.orderLineItems.get(0);
        dueout.product = order.orderLineItems.get(0).product;
        dueoutDao.persist(dueout);
        return dueout;
    }

    @GET
    @Path("getDueout")
    public Dueout getOrder(@QueryParam("dueoutId") String dueoutId) {
        return dueoutDao.findById(new ObjectId(dueoutId));
    }

    @GET
    @Path("getDueouts")
    public List<Dueout> getDueouts() {
        return dueoutDao.findAll().list();
    }
}