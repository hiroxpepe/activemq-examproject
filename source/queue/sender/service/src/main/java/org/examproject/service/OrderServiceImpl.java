package org.examproject.service;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.examproject.model.Response;
import org.examproject.model.Order;
import org.examproject.model.OrderStatus;
import org.examproject.util.CommonUtil;

/**
 * @author h.adachi
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    MessageSender<Order> messageSender;

    @Autowired
    OrderRepository orderRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @Override
    public void send(Order order) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        order.setOrderId(CommonUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.put(order);
        log.info("Application : sending order request {}", order);
        messageSender.send(order);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void updateBy(Response response) {
        Order order = orderRepository.getById(response.getOrderId());
        if (response.getReturnCode() == 200) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (response.getReturnCode() == 300) {
            order.setStatus(OrderStatus.FAILED);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
        orderRepository.put(order);
    }

    @Override
    public Map<String, Order> getAll() {
        return orderRepository.getAll();
    }

}
