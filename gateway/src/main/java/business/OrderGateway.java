package business;

import Data.Dueout;
import Data.Order;
import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import Api.IOrderMicroservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Traced
@Timed
public class OrderGateway {
    private static final Logger LOG = Logger.getLogger(OrderGateway.class);

    @Inject
    @RestClient
    IOrderMicroservice orderMicroservice;

    @Inject
    ProductGateway productGateway;

    @Inject
    DueoutGateway dueoutGateway;

    @Inject
    @Channel("orderRequested")
    Emitter<String> orderEmitter;

    public Order placeOrder(String productName) {
        LOG.info("I'm placing an order");

        Product product = productGateway.getProductByName(productName);
        Order order = orderMicroservice.createNewOrder(product);
        Dueout dueout = dueoutGateway.createDueout(order);
        order = orderMicroservice.recordOrderDueout(dueout);

        return order;
    }

//    @Incoming("orderCreated")
//    public void processOrderCreated(Order order){
//        LOG.info("Order Gateway received orderCreated event");
//        order.orderLineItems.add(new Order.LineItem());
//        order.orderLineItems.get(0).product = productGateway.getProductByName(order.name);
////        order.orderLineItems.get(0).dueout = dueoutGateway.createDueout(order);
//    }
//
//    public String placeOrderAsync(String productName) {
//        LOG.info("I'm requesting an order");
//        orderEmitter.send(productName);
//        return "Order requested";
//    }

    public Order getOrder(String orderId) {
        LOG.info("I'm getting an order for orderId: " + orderId);
        return orderMicroservice.getOrder(orderId);
    }

    public List<Order> getOrders() {
        LOG.info("getting all orders");
        return orderMicroservice.getOrders();

    }
}
