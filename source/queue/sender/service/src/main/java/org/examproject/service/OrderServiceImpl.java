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

import java.util.Map;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.examproject.entity.Response;
import org.examproject.entity.Order;
import org.examproject.repository.OrderRepository;
import org.examproject.value.OrderStatus;
import org.examproject.util.CommonUtil;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final MessageSendAndReceiver<Order, Response> messageSendAndReceiver;

    @NonNull
    private final OrderRepository orderRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(Order order) {
        order.setOrderId(CommonUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED.getName());
        orderRepository.save(order);
        messageSendAndReceiver.send(order);
    }

    @Override
    @Transactional
    public void updateBy(Response response) {
        Order order = orderRepository.findByOrderId(response.getOrderId());
        if (response.getReturnCode() == 200) {
            order.setStatus(OrderStatus.CONFIRMED.getName());
        } else if (response.getReturnCode() == 300) {
            order.setStatus(OrderStatus.FAILED.getName());
        } else {
            order.setStatus(OrderStatus.PENDING.getName());
        }
        orderRepository.save(order);
    }

    @Override
    public Map<String, Order> getAll() {
        return orderRepository.findAll().stream().collect(
            Collectors.toMap(
                order -> order.getOrderId(),
                order -> order
            )
        );
    }

}
