package rest;

import Data.Order;
import business.OrderGateway;
import business.SystemGateway;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/gateway/system")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SystemGatewayApi {

    @Inject
    SystemGateway systemGateway;

    @GET
    @Path("updateEnumCollections")
    @Produces(MediaType.TEXT_PLAIN)
    public void updateEnumCollections() {
        systemGateway.updateEnumCollections();
    }

}
