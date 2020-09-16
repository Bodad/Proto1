package Data;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderDeserializer extends JsonbDeserializer<Order> {
    public OrderDeserializer(){
        super(Order.class);
    }
}
