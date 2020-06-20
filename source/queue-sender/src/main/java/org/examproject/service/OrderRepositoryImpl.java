package org.examproject.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import org.examproject.model.Order;
import org.examproject.service.OrderRepository;

@Service("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

    private final Map<String, Order> orders = new ConcurrentHashMap<String, Order>();

    @Override
    public void putOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public Map<String, Order> getAllOrders() {
        return orders;
    }
    
}
