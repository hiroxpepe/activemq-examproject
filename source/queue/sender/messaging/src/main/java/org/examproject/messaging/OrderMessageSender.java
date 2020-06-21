package org.examproject.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import org.examproject.model.Order;
import org.examproject.service.MessageSender;

/**
 * @author h.adachi
 */
@Component("orderMessageSender")
public class OrderMessageSender implements MessageSender<Order>{

    @Autowired
    JmsTemplate jmsTemplate;

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
