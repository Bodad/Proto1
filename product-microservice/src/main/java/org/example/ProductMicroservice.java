package org.example;

import Data.Product;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Traced
@Timed

@Path("/microservice/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductMicroservice {
    @Inject
    ProductDao productDao;

    @GET
    @Path("getProductByName")
    public Product getProductByName(@QueryParam("productName") String productName) {
        Product product = productDao.find("name", productName).firstResult();
        if (product == null){
            product = new Product();
            product.name = productName;
            productDao.persist(product);
        }
        return product;
    }

    @GET
    @Path("getProduct")
    public Product getProduct(@QueryParam("productId") String productId) {
        return productDao.findById(new ObjectId(productId));
    }

    @GET
    @Path("getProducts")
    public List<Product> getProducts() {
        return productDao.findAll().list();
    }

}