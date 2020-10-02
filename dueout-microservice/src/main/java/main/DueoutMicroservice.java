package main;

import Data.Dueout;
import Data.Order;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Traced
@Path("/microservice/dueout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DueoutMicroservice {
    private static final Logger LOG = Logger.getLogger(DueoutMicroservice.class);

    @Inject
    DueoutDao dueoutDao;

    @Inject
    @Channel("dueoutCreated")
    Emitter<Dueout> dueoutCreatedEmitter;

    @POST
    @Path("createDueout")
    public Dueout createDueout(Order order) {
        Dueout dueout = new Dueout();
        dueout.order = order;
        dueout.product = order.product;
        dueoutDao.persist(dueout);
        return dueout;
    }

//    @Incoming("orderCreated")
//    public void processOrderCreated(Order order){
//        LOG.info("Dueout Microservice received orderCreated event");
//        Dueout dueout = createDueout(order);
//        dueoutCreatedEmitter.send(dueout);
//    }
//

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