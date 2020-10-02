package main;

import Business.MongoDao;
import Data.Dueout;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Traced
@Timed
public class DueoutDao extends MongoDao<Dueout> {
}
