package org.example;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Traced
public class OrderMicroservice {
    private static final Logger LOG = Logger.getLogger(OrderMicroservice.class);

    @Incoming("orders")
    public void processOrder(String orderString){
        LOG.info("I am processing " + orderString);
    }
}
