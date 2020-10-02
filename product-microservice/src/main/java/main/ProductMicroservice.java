package main;

import Data.Product;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.InvalidParameterException;
import java.util.List;

@Traced
@Path("/microservice/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductMicroservice {
    @Inject
    ProductDao productDao;

    @GET
    @Path("getProductByName")
    public Product getProductByName(@QueryParam("productName") String productName) {
        if (productName.contains("Drugs")) throw new InvalidParameterException("No... you can't order drugs");

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