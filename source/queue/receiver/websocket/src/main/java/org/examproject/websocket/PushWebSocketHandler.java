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

package org.examproject.websocket;

import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PushWebSocketHandler extends TextWebSocketHandler {

    @NonNull
    private final ApplicationContext context;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, WebSocketSession> webSocketSessions = context.getBean("concurrentHashMap", Map.class);
        webSocketSessions.clear(); // FIXME:
        webSocketSessions.put(session.getId(), session);
        log.info("map.size: " + webSocketSessions.size());
    }

}
