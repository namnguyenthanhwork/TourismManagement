package com.ou.customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class CHomePageController {

    @GetMapping()
    public String getCustomerHomePage() {
        return "c-homepage";
    }

}
