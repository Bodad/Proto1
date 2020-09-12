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
public interface IOrderMicroservice {

    @POST
    @Path("/createOrder")
    @Produces("application/json")
    Order createOrder(Order order);

    @GET
    @Path("/getOrder")
    @Produces("application/json")
    Order getOrder(@QueryParam("orderId") String orderId);

    @GET
    @Path("/getOrders")
    @Produces("application/json")
    List<Order> getOrders();

}
