package rest;

import Data.Order;
import Data.Product;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
@RegisterRestClient(configKey="order-microservice-api")
@Produces("application/json")
public interface IOrderMicroservice {

    @POST
    @Path("/createOrder")
    Order createOrder(Order order);

    @GET
    @Path("/getOrder")
    Order getOrder(@QueryParam("orderId") String orderId);

    @GET
    @Path("/getOrders")
    List<Order> getOrders();

    @GET
    @Path("/createNewOrder")
    Order createNewOrder();
}
