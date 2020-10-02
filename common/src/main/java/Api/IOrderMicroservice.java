package Api;

import Data.Dueout;
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

    @GET
    @Path("/getOrder")
    Order getOrder(@QueryParam("orderId") String orderId);

    @GET
    @Path("/getOrders")
    List<Order> getOrders();

    @POST
    @Path("/createNewOrder")
    Order createNewOrder(Product product);

    @POST
    @Path("/recordDueOut")
    Order recordOrderDueout(Dueout dueout);
}