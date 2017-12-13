package si.fri.rso.samples.orders.services.streaming.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import si.fri.rso.samples.orders.services.OrdersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Log
public class OrdersConsumer {

    private Logger log = LogManager.getLogger(OrdersConsumer.class.getName());

    @Inject
    private OrdersBean ordersBean;

    @StreamListener(topics = {"xtmm0ew0-default"})
    public void onMessage(ConsumerRecord<String, String> record) {

        log.info(String.format("Consumed message: offset = %d, key = %s, value = %s%n", record.offset(), record.key()
                , record.value()));

        JSONObject message = new JSONObject(record.value());

        String id = message.getString("id");
        String status = message.getString("status");


        log.info("Status for order " + id + " set to " + status);

        ordersBean.setOrderStatus(id, status);

    }

}
