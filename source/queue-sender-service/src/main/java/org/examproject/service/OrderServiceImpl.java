package org.examproject.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.examproject.messaging.OrderMessageSender;
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
    //OrderMessageSender messageSender;
    MessageSender<Order> messageSender;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void sendOrder(Order order) {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        order.setOrderId(BasicUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.putOrder(order);
        LOG.info("Application : sending order request {}", order);
        messageSender.sendMessage(order);
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void updateOrder(Response response) {
        Order order = orderRepository.getOrder(response.getOrderId());
        if (response.getReturnCode() == 200) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (response.getReturnCode() == 300) {
            order.setStatus(OrderStatus.FAILED);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
        orderRepository.putOrder(order);
    }

    public Map<String, Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

}
