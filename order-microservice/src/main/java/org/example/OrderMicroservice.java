package org.example;


import Data.Order;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
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
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Traced
@Timed

@Path("/microservice/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderMicroservice {
    private static final Logger LOG = Logger.getLogger(OrderMicroservice.class);

    @Inject
    OrderDao orderDao;

    @Inject
    @Channel("orderCreated")
    Emitter<Order> orderCreatedEmitter;


    @Incoming("orderRequested")
    public void processOrder(String productName){
        LOG.info("Received orderRequested event");
        Order order = createNewOrder();
        order.name = productName;
        orderCreatedEmitter.send(order);
    }


    @GET
    @Path("createNewOrder")
    public Order createNewOrder(){
        Order order = new Order();
        orderDao.persist(order);
        return order;
    }

    @GET
    @Path("getOrder")
    public Order getOrder(@QueryParam("orderId") String orderId) {
        return orderDao.findById(new ObjectId(orderId));
    }

    @GET
    @Path("getOrders")
    public List<Order> getOrders() {
        return orderDao.findAll().list();
    }

}
