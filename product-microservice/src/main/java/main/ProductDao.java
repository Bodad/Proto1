package main;

import Business.MongoDao;
import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Traced
@Timed
public class ProductDao extends MongoDao<Product> {
}
