package Api;

import Data.Product;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/microservice/product")
@RegisterRestClient(configKey="product-microservice-api")
@Produces("application/json")
@Consumes("application/json")
public interface IProductMicroservice {

    @GET
    @Path("/getProduct")
    Product getProduct(@QueryParam("productId") String productId);

    @GET
    @Path("/getProducts")
    List<Product> getProducts();

    @GET
    @Path("/getProductByName")
    Product getProductByName(@QueryParam("productName") String productName);
}
