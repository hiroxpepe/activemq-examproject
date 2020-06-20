package org.examproject.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

//import org.examproject.model.InventoryResponse;
import org.examproject.model.Order;
//import org.examproject.service.OrderService;

/**
 * @author h.adachi
 */
@Component("orderMessageReceiver")
public class OrderMessageReceiver {

    static final Logger LOG = LoggerFactory.getLogger(OrderMessageReceiver.class);

//    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
//    private static final String ORDER_QUEUE = "order-queue";

    //@Autowired
    //OrderService orderService;

    @JmsListener(destination="order-queue", containerFactory = "containerFactory")
    public void receiveMessage(final Message<Order> message) throws JMSException {
        LOG.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        LOG.info("Application : headers received : {}", headers);

        Order order = (Order) message.getPayload();
        LOG.info("Application : product : {}", order);

        //orderInventoryService.processOrder(order);
        LOG.info("----------------------------------------------------");

    }

}
