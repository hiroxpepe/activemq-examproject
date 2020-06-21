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
import org.examproject.service.MessageSendAndReceiver;
import org.examproject.service.OrderService;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Component("orderMessageSendAndReceiver")
public class OrderMessageSendAndReceiver implements MessageSendAndReceiver<Order, Response>{

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final ApplicationContext context;

    @NonNull
    private final JmsTemplate jmsTemplate;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    public void send(final Order order) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("Application : sending order request {}", order);
        jmsTemplate.setDefaultDestinationName("order-queue");
        jmsTemplate.send((Session session) -> {
            ObjectMessage objectMessage = session.createObjectMessage(order);
            return objectMessage;
        });
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    @JmsListener(destination="response-queue", containerFactory="containerFactory")
    public void receive(final Message<Response> message) throws JMSException {
        log.info("----------------------------------------------------");
        MessageHeaders headers = message.getHeaders();
        log.info("Application : headers received : {}", headers);
        Response response = message.getPayload();
        log.info("Application : product : {}", response);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.updateBy(response);
        log.info("----------------------------------------------------");
    }

}
