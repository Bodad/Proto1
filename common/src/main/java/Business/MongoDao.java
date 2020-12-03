package Business;

import Data.Order;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

public class MongoDao<T> implements PanacheMongoRepository<T> {
    public T findById(String id) {
        return findById(new ObjectId(id));
    }

}
