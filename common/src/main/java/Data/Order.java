package Data;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import javax.json.bind.serializer.JsonbDeserializer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MongoEntity
public class Order{
    public ObjectId id;
    public String name;
    public Date createdDate;
    public Product product;
    public int quantity;
    public Dueout dueout;
}
