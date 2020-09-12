package business;

import Data.Product;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import rest.IProductMicroservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Traced
public class ProductGateway {
    private static final Logger LOG = Logger.getLogger(ProductGateway.class);

    @Inject
    @RestClient
    IProductMicroservice productMicroservice;

//    @Inject
//    @Channel("products")
//    Emitter<String> orderEmitter;

    public Product getProduct(String productId) {
        return productMicroservice.getProduct(productId);
    }

    public List<Product> getProducts() {
        return productMicroservice.getProducts();
    }

}
