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
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import org.examproject.entity.Order;
import org.examproject.service.MessageSender;

/**
 * @author h.adachi
 */
@Slf4j
@Component("orderMessageSender")
public class OrderMessageSender implements MessageSender<Order>{

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @Autowired
    JmsTemplate jmsTemplate;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    public void send(final Order order) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(order);
                return objectMessage;
            }
        });
    }

}
