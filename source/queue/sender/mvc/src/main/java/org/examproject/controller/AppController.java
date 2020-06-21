/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import org.examproject.entity.Order;
import org.examproject.service.OrderService;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class AppController {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final OrderService orderService;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

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
        orderService.send(order);
        model.addAttribute("success", "Order for " + order.getProductName() + " registered.");
        return "orderSuccess";
    }

    @RequestMapping(
        value="/checkStatus.html",
        method=RequestMethod.GET
    )
    public String checkOrderStatus(ModelMap model) {
        model.addAttribute("orders", orderService.getAll());
        return "orderStatus";
    }

}
