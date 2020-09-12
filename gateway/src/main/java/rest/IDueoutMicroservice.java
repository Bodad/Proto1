package rest;

import Data.Dueout;
import Data.Product;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
@RegisterRestClient(configKey="dueout-microservice-api")
public interface IDueoutMicroservice {

    @POST
    @Path("/createDueout")
    @Produces("application/json")
    Dueout createDueout(Dueout dueout);

    @GET
    @Path("/getDueout")
    @Produces("application/json")
    Dueout getDueout(@QueryParam("dueoutId") String dueoutId);

    @GET
    @Path("/getDueouts")
    @Produces("application/json")
    List<Dueout> getDueouts();
}
