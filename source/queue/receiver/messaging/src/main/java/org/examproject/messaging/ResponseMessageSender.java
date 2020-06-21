package org.examproject.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import org.examproject.model.Response;
import org.examproject.service.MessageSender;

@Component("responseMessageSender")
public class ResponseMessageSender implements MessageSender<Response> {

    //@Autowired
    JmsTemplate jmsTemplate;

    public void send(final Response response) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(response);
                return objectMessage;
            }
        });
    }

}
