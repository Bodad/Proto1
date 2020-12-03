package main;

import Api.IDueoutMicroservice;
import Data.Dueout;
import Data.Order;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Traced
@ApplicationScoped
public class DueoutMicroservice implements IDueoutMicroservice {
    private static final Logger LOG = Logger.getLogger(DueoutMicroservice.class);

    @Inject
    DueoutDao dueoutDao;

    @Inject
    @Channel("dueoutCreated")
    Emitter<Dueout> dueoutCreatedEmitter;

    public Dueout createDueout(Order order) {
        Dueout dueout = new Dueout();
        dueout.order = order;
        dueout.product = order.product;
        dueoutDao.persist(dueout);
        return dueout;
    }

    @Override
    public Dueout getDueout(String dueoutId) {
        return dueoutDao.findById(dueoutId);
    }

    @Incoming("orderCreated")
    public void processOrderCreated(Order order) {
        Dueout dueout = createDueout(order);
        dueoutCreatedEmitter.send(dueout);
    }


    @Override
    public List<Dueout> getDueouts() {
        return dueoutDao.listAll();
    }
}