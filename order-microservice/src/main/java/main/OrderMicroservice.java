package main;


import Api.IOrderMicroservice;
import Data.Dueout;
import Data.Order;
import Data.Product;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Traced
@Path("/microservice/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderMicroservice implements IOrderMicroservice {
    private static final Logger LOG = Logger.getLogger(OrderMicroservice.class);

    @Inject
    OrderDao orderDao;

    @Inject
    @Channel("orderCreated")
    Emitter<Order> orderCreatedEmitter;


//    @Incoming("orderRequested")
//    public void processOrder(String productName){
//        LOG.info("Order Microservice received orderRequested event");
//        Order order = createNewOrder();
//        Order.LineItem lineItem = new Order.LineItem();
//        lineItem.product = new Product();
//        lineItem.product.name = productName;
//
//        order.orderLineItems.add(lineItem);
//        order.name = "Order for " + productName;
//        orderCreatedEmitter.send(order);
//    }
//
//    @Incoming("dueoutCreated")
//    public void processDueoutCreated(Dueout dueout){
//        LOG.info("Order Microservice received dueoutCreated event");
//        Order order = orderDao.findById(dueout.order.id);
//        order.orderLineItems.get(0).dueout = dueout;
//        orderDao.persist(order);
//    }
//

    @GET
    @Path("getOrder")
    public Order getOrder(@QueryParam("orderId") String orderId) {
        return orderDao.findById(new ObjectId(orderId));
    }

    @GET
    @Path("getOrders")
    public List<Order> getOrders() {
        return orderDao.findAll().list();
    }

    @Override
    public Order createNewOrder(Product product) {
        Order order = new Order();
        order.product = product;
        order.quantity = 1;
        order.createdDate = new Date();
        orderDao.persist(order);
        return order;
    }

    @Override
    public Order recordOrderDueout(Dueout dueout) {
        Order order = orderDao.findById(dueout.order.id);
        order.dueout = dueout;
        orderDao.update(order);
        return order;
    }

}
