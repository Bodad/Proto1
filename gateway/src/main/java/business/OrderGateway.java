package business;

import Data.Dueout;
import Data.Order;
import Data.Product;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import rest.IOrderMicroservice;
import rest.IProductMicroservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
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

    //    @Inject
//    @Channel("orders")
//    Emitter<String> orderEmitter;

    public Order placeOrder(String productName) {
        LOG.info("I'm placing an order");
        Order order = orderMicroservice.createNewOrder();
        order.orderLineItems.add(new Order.LineItem());
        order.orderLineItems.get(0).product = productGateway.getProductByName(productName);
        order.orderLineItems.get(0).dueout = dueoutGateway.createDueout(order);
        return order;
//        orderEmitter.send(order.toString());
    }

    public Order getOrder(String orderId) {
        LOG.info("I'm getting an order for orderId: " + orderId);
        return orderMicroservice.getOrder(orderId);
//        LOG.info("getting order now");
//        if (Order.count() == 0){
//            Order newOrder = new Order();
//            newOrder.name = "First Order";
//            newOrder.createdDate = new Date();
//            Order.LineItem lineItem = new Order.LineItem();
//            lineItem.price = 3;
//            Product product = new Product();
//            product.name = "Breakfast Sandwich";
//
//            lineItem.product = product;
//            lineItem.quantity = 2;
//
//            newOrder.orderLineItems.add(lineItem);
//            placeOrder(newOrder);
//        }
//
//        Order order = Order.findAll().firstResult();
//  //      orderEmitter.send(order.toString());
//        Order order = new Order();
//        return order;
    }

    public List<Order> getOrders() {
        LOG.info("getting all orders");
        return orderMicroservice.getOrders();

    }
}
