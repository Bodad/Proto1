package Data;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class DueoutDeserializer extends JsonbDeserializer<Dueout> {
    public DueoutDeserializer(){
        super(Dueout.class);
    }
}
