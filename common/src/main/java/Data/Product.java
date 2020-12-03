package Data;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import org.bson.types.ObjectId;

public class Product {
    public ObjectId id;
    public String name;
    public String description;

    public Product(){}
    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }

    public static class ProductDeserializer extends JsonbDeserializer<Product> {
        public ProductDeserializer() {
            super(Product.class);
        }
    }
}
