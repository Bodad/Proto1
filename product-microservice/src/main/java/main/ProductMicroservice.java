package main;

import Api.IProductMicroservice;
import Data.Product;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.List;

@Traced
@ApplicationScoped
public class ProductMicroservice implements IProductMicroservice {
    @Inject
    ProductDao productDao;

    @Override
    public Product getProductByName(String productName) {
        if (productName.contains("Drugs")) throw new InvalidParameterException("No... you can't order drugs");

        Product product = productDao.find("name", productName).firstResult();
        if (product == null) {
            product = new Product();
            product.name = productName;
            productDao.persist(product);
        }
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        return productDao.findById(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.listAll();
    }

}