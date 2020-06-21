package org.examproject.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.examproject.model.Response;
import org.examproject.model.Order;
import org.examproject.model.OrderStatus;
import org.examproject.util.BasicUtil;

/**
 * @author h.adachi
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    MessageSender<Order> messageSender;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void send(Order order) {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        order.setOrderId(BasicUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.put(order);
        LOG.info("Application : sending order request {}", order);
        messageSender.send(order);
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
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

    public Map<String, Order> getAll() {
        return orderRepository.getAll();
    }

}
