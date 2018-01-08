package si.fri.rso.samples.orders.api.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService(value = "rso-order", ttl = 20, pingInterval = 15)
public class OrdersApplication extends Application {
}
