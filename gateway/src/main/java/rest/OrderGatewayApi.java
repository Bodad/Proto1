package rest;

import Data.Order;
import Data.Product;
import business.OrderGateway;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/gateway/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderGatewayApi {

    @Inject
    OrderGateway orderGateway;

    @GET
    @Path("placeOrder")
    public Order placeOrder(@QueryParam("productName") String productName, @QueryParam("order.Type") Order.Type type) {
        return orderGateway.placeOrder(productName, type);
    }

    @GET
    @Path("placeOrderAsync")
    public Order placeOrderAsync(@QueryParam("productName") String productName, @QueryParam("order.Type") Order.Type type){
        return orderGateway.placeOrderAsync(productName, type);
    }

    @GET
    @Path("getOrder")
    public Order getOrder(@QueryParam("orderId") String orderId) {
        return orderGateway.getOrder(orderId);
    }

    @GET
    @Path("getOrders")
    public List<Order> getOrders() {
        return orderGateway.getOrders();
    }
}
