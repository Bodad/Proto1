package org.example;

import Data.Product;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/microservice/product")
@Traced
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductMicroservice {

    @GET
    @Path("getProduct")
    public Product getProduct(@QueryParam("productId") String productId) {
        Product product = new Product();
        product.name = productId;

        return product;
    }

    @GET
    @Path("getProducts")
    public List<Product> getProducts() {
        Product product = new Product();
        product.name = "Widget";

        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

}