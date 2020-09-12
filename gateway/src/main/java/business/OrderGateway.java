package business;

import Data.Dueout;
import Data.Order;
import Data.Product;
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

    public void placeOrder(Order order) {
        Product product = productGateway.getProduct(order.orderLineItems.get(0).product.name);
        orderMicroservice.createOrder(order);

        Dueout dueout = new Dueout();
        dueout.order = order;
        dueout.lineItem = order.orderLineItems.get(0);
        dueout.product = product;
        dueoutGateway.createDueout(dueout);

//        orderEmitter.send(order.toString());
    }

    public Order getOrder(String orderId) {
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
