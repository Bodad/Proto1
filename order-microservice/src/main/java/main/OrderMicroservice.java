package main;


import Api.IOrderMicroservice;
import Data.Dueout;
import Data.Order;
import Data.Product;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Traced
@ApplicationScoped
public class OrderMicroservice implements IOrderMicroservice {
    private static final Logger LOG = Logger.getLogger(OrderMicroservice.class);

    @Inject
    OrderDao orderDao;

    @Inject
    @Channel("orderCreated")
    Emitter<Order> orderCreatedEmitter;

    @Incoming("dueoutCreated")
    public void processDueoutCreated(Dueout dueout) {
        recordOrderDueout(dueout);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.listAll();
    }

    @Override
    public Order createNewOrder(Product product, Order.Type type) {
        Order order = new Order();
        order.product = product;
        order.type = type;
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
