package org.examproject.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import org.examproject.model.Order;

/**
 * @author h.adachi
 */
@Service("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

    private final Map<String, Order> orderMap = new ConcurrentHashMap<String, Order>();

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @Override
    public void put(Order order) {
        orderMap.put(order.getOrderId(), order);
    }

    @Override
    public Order getById(String orderId) {
        return orderMap.get(orderId);
    }

    @Override
    public Map<String, Order> getAll() {
        return orderMap;
    }

}
