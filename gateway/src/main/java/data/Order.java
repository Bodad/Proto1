package data;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends PanacheMongoEntity {
    public String name;
    public Date createdDate;
    public List<LineItem> orderLineItems = new ArrayList<>();

    public static class LineItem{
        public Product product;
        public int quantity;
        public int price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}
