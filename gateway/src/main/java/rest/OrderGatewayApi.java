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
    public void placeOrder(@QueryParam("productName") String productName) {
        Order order = new Order();
        order.name = "Order for " + productName;
        Order.LineItem lineItem = new Order.LineItem();

        Product product = new Product();
        product.name = productName;

        lineItem.product = product;
        lineItem.quantity = 1;

        order.orderLineItems.add(lineItem);
        orderGateway.placeOrder(order);
    }

    @GET
    @Path("getOrder")
    public Order getOrder(@QueryParam("orderId") String orderId) {
        return orderGateway.getOrder(orderId);
    }

    @GET
    @Path("getOrders")
    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    public List<Order> getOrders() {
        return orderGateway.getOrders();
    }
}
