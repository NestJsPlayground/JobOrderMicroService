package si.fri.rso.samples.orders.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.samples.orders.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class OrdersBean {

    private Logger log = Logger.getLogger(OrdersBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Order> getOrders(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Order.class, queryParameters);

    }

    public Order getOrder(String orderId) {

        Order order = em.find(Order.class, orderId);

        if (order == null) {
            throw new NotFoundException();
        }

        return order;
    }

    public Order createOrder(Order order) {

        try {
            beginTx();
            em.persist(order);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return order;
    }

    public Order putOrder(String orderId, Order order) {

        Order c = em.find(Order.class, orderId);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            order.setId(c.getId());
            order = em.merge(order);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return order;
    }

    public void setOrderStatus(String orderId, String status) {

        Order order = em.find(Order.class, orderId);

        if (order == null) {
            throw new NotFoundException();
        }

        try {
            beginTx();
            order.setStatus(status);
            order = em.merge(order);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

    }

    public boolean deleteOrder(String orderId) {

        Order order = em.find(Order.class, orderId);

        if (order != null) {
            try {
                beginTx();
                em.remove(order);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
