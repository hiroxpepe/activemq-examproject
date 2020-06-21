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
