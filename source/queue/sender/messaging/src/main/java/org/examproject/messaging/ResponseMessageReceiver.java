package org.examproject.messaging;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import org.examproject.model.Response;
//import org.examproject.service.OrderInventoryService;

@Slf4j
@Component("responseMessageReceiver")
public class ResponseMessageReceiver {

    //@Autowired
    //OrderInventoryService orderInventoryService;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @JmsListener(destination="response-queue", containerFactory="containerFactory")
    public void receiveMessage(final Message<Response> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);

        Response response = message.getPayload();
        log.info("Application : product : {}", response);

        //orderInventoryService.processOrder(response);
        log.info("----------------------------------------------------");
    }

}
