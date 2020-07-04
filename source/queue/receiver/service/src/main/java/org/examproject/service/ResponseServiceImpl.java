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

package org.examproject.service;

import java.io.IOException;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import org.examproject.entity.Response;
import org.examproject.entity.Order;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Service("responseService")
public class ResponseServiceImpl implements ResponseService {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final ApplicationContext context;

    @NonNull
    private final MessageReceiveAndSender<Order, Response> messageReceiveAndSender;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    public void processBy(Order order){
        // perform any business logic.
        Response response = prepareResponse(order);
        messageReceiveAndSender.send(response);
        // push the message to client.
        Map<String, WebSocketSession> webSocketSessions = context.getBean("concurrentHashMap", Map.class);
        log.info("map.size: " + webSocketSessions.size());
        webSocketSessions.forEach((key, value) -> {
            try {
                value.sendMessage(new TextMessage("push"));
            } catch (IOException ex) {
                log.error(ex.getMessage()); // FIXME:
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Methods

    private Response prepareResponse(Order order) {
        Response response = new Response();
        response.setOrderId(order.getOrderId());
        response.setReturnCode(200);
        response.setComment("Order Processed successfully");
        return response;
    }

}
