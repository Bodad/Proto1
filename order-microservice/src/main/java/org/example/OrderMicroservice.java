package org.example;


import Data.Order;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Traced
@Path("/microservice/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderMicroservice {
    private static final Logger LOG = Logger.getLogger(OrderMicroservice.class);

//    @Incoming("orders")
//    public void processOrder(String orderString){
////        Order.persistOrUpdate(order);
//
//        LOG.info("I am processing " + orderString);
//    }


    @POST
    @Path("createOrder")
    public Order createOrder(Order order) {
        return order;
    }

    @GET
    @Path("getOrder")
    public Order getOrder(@QueryParam("orderId") String orderId) {
        Order order = new Order();
        order.name = orderId;

        return order;
    }

    @GET
    @Path("getOrders")
    public List<Order> getOrders() {
        Order order = new Order();
        order.name = "New Order";
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }

}
