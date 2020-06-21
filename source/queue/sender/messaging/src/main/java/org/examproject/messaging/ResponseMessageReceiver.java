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
