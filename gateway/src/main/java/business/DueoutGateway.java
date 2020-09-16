package business;

import Data.Dueout;
import Data.Order;
import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import rest.IDueoutMicroservice;
import rest.IProductMicroservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Traced
@Timed
public class DueoutGateway {
    private static final Logger LOG = Logger.getLogger(DueoutGateway.class);

    @Inject
    @RestClient
    IDueoutMicroservice microservice;

//    @Inject
//    @Channel("products")
//    Emitter<String> orderEmitter;

    public Dueout getDueout(String dueoutId) {
        LOG.info("Getting Dueout info for dueout id: " + dueoutId);
        return microservice.getDueout(dueoutId);
    }

    public List<Dueout> getDueouts() {
        LOG.info("Getting info for all dueouts");
        return microservice.getDueouts();
    }

    public Dueout createDueout(Order order) {
        LOG.info("Creating a due out for order Id: " + order.id);
        return microservice.createDueout(order);
    }
}
