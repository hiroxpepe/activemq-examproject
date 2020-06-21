package org.examproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author h.adachi
 */
@Slf4j
@Controller
public class AppController {

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @RequestMapping(
        value="/home.html",
        method=RequestMethod.GET
    )
    public String prepareProduct(ModelMap model) {
        return "home";
    }

}
