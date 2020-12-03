package Data;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import javax.json.bind.serializer.JsonbDeserializer;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MongoEntity
public class Order {
    public ObjectId id;
    public String name;
    public Type type;
    public Date createdDate;
    public Product product;
    public int quantity;
    public Dueout dueout;

    public enum Type{
        LOGICOLE("LogiCole", true, new Product("test1", "This is a test"), Date.from(Instant.now().plus(Period.ofDays(2)))),
        TEWLS("Tewls", true, new Product("Frenchie", "A french bulldog"), Date.from(Instant.now().plus(Period.ofWeeks(1)))),
        EHR("Electronic Health Record", false, new Product("Uber", "Fully certified"), new Date());

        public final String name;
        public final boolean isInternal;
        public final Product product;
        public final Date dueDate;

        Type(String name, boolean isInternal, Product product, Date dueDate) {
            this.name = name;
            this.isInternal = isInternal;
            this.product = product;
            this.dueDate = dueDate;
        }
    }
}
