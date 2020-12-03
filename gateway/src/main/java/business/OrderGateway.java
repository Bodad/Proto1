package business;

import Data.Dueout;
import Data.Order;
import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
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
    @Channel("orderCreated")
    Emitter<Order> orderCreatedEmitter;
    public Order placeOrder(String productName, Order.Type type) {
        LOG.info("I'm placing an order");

        Product product = productGateway.getProductByName(productName);
        Order order = orderMicroservice.createNewOrder(product, type);
        Dueout dueout = dueoutGateway.createDueout(order);
        order = orderMicroservice.recordOrderDueout(dueout);

        return order;
    }

    public Order placeOrderAsync(String productName, Order.Type type) {
        LOG.info("I'm requesting an order");
        Product product = productGateway.getProductByName(productName);
        Order order = orderMicroservice.createNewOrder(product, type);
        orderCreatedEmitter.send(order);
        return order;
    }

    public Order getOrder(String orderId) {
        LOG.info("I'm getting an order for orderId: " + orderId);
        return orderMicroservice.getOrder(orderId);
    }

    public List<Order> getOrders() {
        LOG.info("getting all orders");
        return orderMicroservice.getOrders();

    }
}
