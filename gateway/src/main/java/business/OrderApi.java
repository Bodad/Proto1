package business;

import data.Order;
import data.Product;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;


@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderApi {

    private static final Logger LOG = Logger.getLogger(OrderApi.class);
//    @Inject
//    @Channel("order-create")
//    Emitter<Order> orderEmitter;

    @POST
    public void placeOrder(Order order) {
        Order.persistOrUpdate(order);
//        orderEmitter.send(order);
    }

    @GET
    @Path("getOrder")
    public Order getOrder() {
        LOG.info("getting order");
        if (Order.count() == 0){
            Order newOrder = new Order();
            newOrder.name = "First Order";
            newOrder.createdDate = new Date();
            Order.LineItem lineItem = new Order.LineItem();
            lineItem.price = 3;
            Product product = new Product();
            product.name = "Breakfast Sandwich";

            lineItem.product = product;
            lineItem.quantity = 2;

            newOrder.orderLineItems.add(lineItem);
            Order.persistOrUpdate(newOrder);
        }
        return Order.findAll().firstResult();
    }

    @GET
    @Path("getOrders")
    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    public List<Order> getOrders() {
        LOG.info("getting all orders");
        return Order.findAll().list();
    }


}
