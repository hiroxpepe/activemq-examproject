package org.examproject.controller;

import javax.validation.Valid;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.examproject.model.Order;
import org.examproject.service.OrderService;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class AppController {

    @NonNull
    private final OrderService orderService;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @RequestMapping(
        value="/home.html",
        method=RequestMethod.GET
    )
    public String prepareProduct(ModelMap model) {
        return "home";
    }

    @RequestMapping(
        value="/newOrder.html",
        method=RequestMethod.GET
    )
    public String prepareOrder(ModelMap model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping(
        value="/newOrder.html",
        method=RequestMethod.POST
    )
    public String sendOrder(
        @Valid Order order,
        BindingResult result,
        ModelMap model
    ) {
        if (result.hasErrors()) {
            return "order";
        }
        orderService.sendOrder(order);
        model.addAttribute("success", "Order for " + order.getProductName() + " registered.");
        return "ordersuccess";
    }

    @RequestMapping(
        value="/checkStatus.html",
        method=RequestMethod.GET
    )
    public String checkOrderStatus(ModelMap model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orderStatus";
    }

}
