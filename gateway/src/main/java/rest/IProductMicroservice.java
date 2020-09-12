package rest;

import Data.Product;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
@RegisterRestClient(configKey="product-microservice-api")
public interface IProductMicroservice {

    @GET
    @Path("/getProduct")
    @Produces("application/json")
    Product getProduct(@QueryParam("productId") String productId);

    @GET
    @Path("/getProducts")
    @Produces("application/json")
    List<Product> getProducts();
}
