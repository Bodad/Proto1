package rest;

import Data.Order;
import business.OrderGateway;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Path("/gateway/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemoGatewayApi {

    private static Random random = new Random();

    @GET
    @Path("executeFlakyMethod")
    public String executeFlakyMethod() {
        int i = random.nextInt(2);
        if (i == 0) throw new IllegalStateException("Sorry Charlie");
        return "I succeeded";
    }

    @GET
    @Path("executeLongRunningMethod")
    public String executeLongRunningMethod() throws InterruptedException{
            randomDelay(2000);
            return "I ran to completion";
    }

    private void randomDelay(int maxTime) throws InterruptedException {
        Thread.sleep(random.nextInt(maxTime));
    }

    @GET
    @Path("executeCacheMethod")
    @CacheResult(cacheName = "test-cache")
    public String executeCacheMethod() throws InterruptedException {
        Thread.sleep(5000);
        Date now = new Date();
        return "I actually ran on " + now.toString();
    }

    @GET
    @Path("executeUnCacheMethod")
    @CacheInvalidate(cacheName = "test-cache")
    public String executeUnCacheMethod(){
        return "OK - I cleared cache";
    }



// ---------------------------------------------------


    @GET
    @Path("executeFlakyMethod1")
    @Retry(maxRetries=4)
    public String executeFlakyMethod1() {
        int i = random.nextInt(2);
        if (i == 0) throw new IllegalStateException("Sorry Charlie");
        return "I succeeded";
    }




    @GET
    @Path("executeLongRunningMethod1")
    @Timeout(1000)
    public String executeLongRunningMethod1() throws InterruptedException{
        try {
            randomDelay(2000);
            return "I ran to completion";
        }catch(InterruptedException e){
            return "I got interrupted";
        }
    }








    // ----------------------------------------------


    @GET
    @Path("executeLongRunningMethod2")
    @Timeout(1000)
    @Fallback(fallbackMethod = "fallbackLongRunningMethod")
    public String executeLongRunningMethod2() throws InterruptedException{
        randomDelay(2000);
        return "I ran to completion";
    }

    public String fallbackLongRunningMethod(){
        return "Well here is something at least";
    }



    @GET
    @Path("executeFlakyMethod2")
    @Retry(maxRetries=4)
    @Fallback(fallbackMethod = "fallbackLongRunningMethod")
    public String executeFlakyMethod2() {
        int i = random.nextInt(2);
        if (i == 0) throw new IllegalStateException("Sorry Charlie");
        return "I succeeded";
    }



}
