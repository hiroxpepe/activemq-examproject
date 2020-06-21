package org.examproject.service;

import java.util.Map;

import org.examproject.entity.Response;
import org.examproject.entity.Order;

/**
 * @author h.adachi
 */
public interface OrderService {

    public void send(Order order);

    public void updateBy(Response response);

    public Map<String, Order> getAll();
}
