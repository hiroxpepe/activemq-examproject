package org.examproject.messaging;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import org.examproject.entity.Response;
import org.examproject.service.OrderService;

@Slf4j
@Component("responseMessageReceiver")
public class ResponseMessageReceiver {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @Autowired
    OrderService orderService;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @JmsListener(destination="response-queue", containerFactory="containerFactory")
    public void receiveMessage(final Message<Response> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);

        Response response = message.getPayload();
        log.info("Application : product : {}", response);

        orderService.updateBy(response);
        log.info("----------------------------------------------------");
    }

}
