package rest;

import business.OrderGateway;
import data.Order;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderGatewayApi {

    @Inject
    OrderGateway orderGateway;

    @POST
    public void placeOrder(Order order) {
        orderGateway.placeOrder(order);
    }

    @GET
    @Path("getOrder")
    public Order getOrder() {
        return orderGateway.getOrder();
    }

    @GET
    @Path("getOrders")
    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    public List<Order> getOrders() {
        return orderGateway.getOrders();
    }
}
