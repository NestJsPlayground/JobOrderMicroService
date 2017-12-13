package si.fri.rso.samples.orders.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import si.fri.rso.samples.orders.Order;
import si.fri.rso.samples.orders.services.OrdersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class OrdersResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private OrdersBean ordersBean;

    @GET
    @Metered
    public Response getOrders() {

        //List<Order> orders = ordersBean.getOrders(uriInfo);

        return Response.ok(new ArrayList<Order>()).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("orderId") String orderId) {

       /* Order order = ordersBean.getOrder(orderId);

        if (order == null) {

        }

        return Response.status(Response.Status.OK).entity(order).build();*/
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createOrder(Order order) {

        return Response.status(Response.Status.CREATED).build();
        /*
        if (order.getTitle() == null || order.getTitle().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            order = ordersBean.createOrder(order);
        }

        if (order.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(order).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(order).build();
        }*/
    }

    @PUT
    @Path("{orderId}")
    public Response putZavarovanec(@PathParam("orderId") String orderId, Order order) {

        /*
        order = ordersBean.putOrder(orderId, order);

        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (order.getId() != null)
                return Response.status(Response.Status.OK).entity(order).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }*/
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{orderId}")
    public Response deleteCustomer(@PathParam("orderId") String orderId) {

        /*boolean deleted = ordersBean.deleteOrder(orderId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }*/
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
