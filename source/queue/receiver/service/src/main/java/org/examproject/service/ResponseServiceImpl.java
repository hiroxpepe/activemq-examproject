package org.examproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.examproject.model.Response;
import org.examproject.model.Order;

@Service("responseService")
public class ResponseServiceImpl implements ResponseService {

    static final Logger LOG = LoggerFactory.getLogger(ResponseServiceImpl.class);

    @Autowired
    MessageSender<Response> messageSender;

    @Override
    public void processBy(Order order) {

        //Perform any business logic.
        Response response = prepareResponse(order);
        LOG.info("Inventory : sending order confirmation {}", response);
        messageSender.send(response);
    }

    private Response prepareResponse(Order order) {
        Response response = new Response();
        response.setOrderId(order.getOrderId());
        response.setReturnCode(200);
        response.setComment("Order Processed successfully");
        return response;
    }

}
