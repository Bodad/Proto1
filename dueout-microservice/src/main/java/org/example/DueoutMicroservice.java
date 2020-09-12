package org.example;

import Data.Dueout;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/microservice/dueout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Traced
public class DueoutMicroservice {

    @POST
    @Path("createDueout")
    public Dueout createDueout(Dueout dueout) {
        return dueout;
    }

    @GET
    @Path("getDueout")
    public Dueout getOrder(@QueryParam("dueoutId") String dueoutId) {
        Dueout dueout = new Dueout();
        dueout.name = dueoutId;
        return dueout;
    }

    @GET
    @Path("getDueouts")
    public List<Dueout> getDueouts() {
        Dueout dueout = new Dueout();
        dueout.name = "New Dueout";
        List<Dueout> dueouts = new ArrayList<>();
        dueouts.add(dueout);
        return dueouts;
    }
}