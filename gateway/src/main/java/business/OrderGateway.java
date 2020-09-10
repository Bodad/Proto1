package business;

import Data.Order;
import Data.Product;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Traced
public class OrderGateway {
    private static final Logger LOG = Logger.getLogger(OrderGateway.class);

    @Inject
    @Channel("orders")
    Emitter<Order> orderEmitter;

    public void placeOrder(Order order) {
        orderEmitter.send(order);
    }

    public Order getOrder() {
        LOG.info("getting order now");
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
            placeOrder(newOrder);
        }

        Order order = Order.findAll().firstResult();
        orderEmitter.send(order);

        return order;
    }

    public List<Order> getOrders() {
        LOG.info("getting all orders");
        return Order.findAll().list();
    }
}
