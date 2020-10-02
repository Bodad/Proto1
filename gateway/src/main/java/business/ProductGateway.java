package business;

import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import Api.IProductMicroservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Traced
@Timed
public class ProductGateway {
    private static final Logger LOG = Logger.getLogger(ProductGateway.class);

    @Inject
    @RestClient
    IProductMicroservice productMicroservice;

    //    @Inject
//    @Channel("products")
//    Emitter<String> orderEmitter;

    public Product getProduct(String productId) {
        LOG.info("Getting product info for product Id: " + productId);
        return productMicroservice.getProduct(productId);
    }

    public List<Product> getProducts() {
        LOG.info("Getting product info for all products");
        return productMicroservice.getProducts();
    }

    public Product getProductByName(String productName) {
        LOG.info("Getting product info by name: " + productName);
        return productMicroservice.getProductByName(productName);
    }
}
