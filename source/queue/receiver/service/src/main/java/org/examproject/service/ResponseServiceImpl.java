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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.examproject.entity.Response;
import org.examproject.entity.Order;

@Slf4j
@Service("responseService")
public class ResponseServiceImpl implements ResponseService {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @Autowired
    MessageReceiveAndSebder<Order, Response> messageSender;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    public void processBy(Order order) {
        // Perform any business logic.
        Response response = prepareResponse(order);
        messageSender.send(response);
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
