package rest;

import Data.Product;
import business.ProductGateway;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/gateway/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductGatewayApi {

    @Inject
    ProductGateway productGateway;

    @GET
    @Path("getProduct")
    public Product getProduct(@QueryParam("productId") String productId) {
        return productGateway.getProduct(productId);
    }

}
