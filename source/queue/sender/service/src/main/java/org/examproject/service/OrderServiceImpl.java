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
    private final MessageSender<Order> messageSender;

    @NonNull
    private final OrderRepository orderRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    @Transactional
    public void send(Order order) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        order.setOrderId(CommonUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED.getName());
        orderRepository.save(order);
        log.info("Application : sending order request {}", order);
        messageSender.send(order);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    @Transactional
    public void updateBy(Response response) {
        int i = 0;
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
    @Transactional
    public Map<String, Order> getAll() {
//        Map<String, Order> orderMap = orderRepository.findAll().stream().collect(
//            Collectors.toMap(
//                order -> order.getOrderId(),
//                order -> order
//            )
//        );
        return orderRepository.findAll().stream().collect(
            Collectors.toMap(
                order -> order.getOrderId(),
                order -> order
            )
        );
    }

}
