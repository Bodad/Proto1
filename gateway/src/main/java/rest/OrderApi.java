package rest;

import business.OrderService;
import data.Order;
import data.Product;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;


@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderApi{

    @Inject
    OrderService orderService;

    @POST
    public void placeOrder(Order order) {
        orderService.placeOrder(order);
    }

    @GET
    @Path("getOrder")
    public Order getOrder() {
        return orderService.getOrder();
    }

    @GET
    @Path("getOrders")
    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
