package org.examproject.service;

import java.util.Map;

import org.examproject.model.Response;
import org.examproject.model.Order;

/**
 * @author h.adachi
 */
public interface OrderService {

    public void sendOrder(Order order);

    public void updateOrder(Response response);

    public Map<String, Order> getAllOrders();
}
