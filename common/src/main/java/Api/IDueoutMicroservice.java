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

@Path("/microservice/dueout")
@RegisterRestClient(configKey="dueout-microservice-api")
@Produces("application/json")
public interface IDueoutMicroservice {

    @POST
    @Path("/createDueout")
    Dueout createDueout(Order order);

    @GET
    @Path("/getDueout")
    Dueout getDueout(@QueryParam("dueoutId") String dueoutId);

    @GET
    @Path("/getDueouts")
    List<Dueout> getDueouts();
}
