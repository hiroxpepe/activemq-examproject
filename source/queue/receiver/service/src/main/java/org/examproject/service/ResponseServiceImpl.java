package org.examproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.examproject.model.Response;
import org.examproject.model.Order;

@Slf4j
@Service("responseService")
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    MessageSender<Response> messageSender;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @Override
    public void processBy(Order order) {

        // Perform any business logic.
        Response response = prepareResponse(order);
        log.info("Inventory : sending order confirmation {}", response);
        messageSender.send(response);
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods

    private Response prepareResponse(Order order) {
        Response response = new Response();
        response.setOrderId(order.getOrderId());
        response.setReturnCode(200);
        response.setComment("Order Processed successfully");
        return response;
    }

}
