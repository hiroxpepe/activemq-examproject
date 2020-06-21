/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.examproject.messaging;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import org.examproject.entity.Order;
import org.examproject.entity.Response;
import org.examproject.service.ResponseService;
import org.examproject.service.MessageReceiveAndSender;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Component("orderMessageReceiveAndSender")
public class OrderMessageReceiveAndSender implements MessageReceiveAndSender<Order, Response> {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final ApplicationContext context;

    @NonNull
    private final JmsTemplate jmsTemplate;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    @JmsListener(destination="order-queue", containerFactory="containerFactory")
    public void receive(final Message<Order> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);
        Order order = message.getPayload();
        log.info("Application : product : {}", order);
        ResponseService responseService = context.getBean(ResponseService.class);
        responseService.processBy(order);
        log.info("----------------------------------------------------");
    }

    @Override
    public void send(final Response response) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("Inventory : sending order confirmation {}", response);
        jmsTemplate.setDefaultDestinationName("response-queue");
        jmsTemplate.send((Session session) -> {
            ObjectMessage objectMessage = session.createObjectMessage(response);
            return objectMessage;
        });
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
