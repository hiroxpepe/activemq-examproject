package org.examproject.messaging;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import org.examproject.entity.Order;
import org.examproject.service.ResponseService;

/**
 * @author h.adachi
 */
@Slf4j
@Component("orderMessageReceiver")
public class OrderMessageReceiver {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @Autowired
    ResponseService responseService;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @JmsListener(destination="order-queue", containerFactory="containerFactory")
    public void receiveMessage(final Message<Order> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);

        Order order = message.getPayload();
        log.info("Application : product : {}", order);

        responseService.processBy(order);
        log.info("----------------------------------------------------");
    }

}
