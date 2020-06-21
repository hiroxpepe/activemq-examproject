package org.examproject.messaging;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import org.examproject.model.Order;
//import org.examproject.service.OrderService;

/**
 * @author h.adachi
 */
@Slf4j
@Component("orderMessageReceiver")
public class OrderMessageReceiver {

    //@Autowired
    //OrderService orderService;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @JmsListener(destination="order-queue", containerFactory="containerFactory")
    public void receiveMessage(final Message<Order> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);

        Order order = (Order) message.getPayload();
        log.info("Application : product : {}", order);

        //orderInventoryService.processOrder(order);
        log.info("----------------------------------------------------");
    }

}
