package business;

import Data.Dueout;
import Data.Product;
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
public class DueoutGateway {
    private static final Logger LOG = Logger.getLogger(DueoutGateway.class);

    @Inject
    @RestClient
    IDueoutMicroservice microservice;

//    @Inject
//    @Channel("products")
//    Emitter<String> orderEmitter;

    public Dueout getDueout(String dueoutId) {
        return microservice.getDueout(dueoutId);
    }

    public List<Dueout> getDueouts() {
        return microservice.getDueouts();
    }

    public Dueout createDueout(Dueout dueout) {
        return microservice.createDueout(dueout);
    }
}
