package com.ou.customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CHomePageController {

    @GetMapping("/")
    public String getCustomerHomePage() {
        return "c-homepage";
    }

    @GetMapping("/tin-tuc")
    public String getCustomerNews() {
        return "c-news";
    }

    @GetMapping("/chi-tiet-tour")
    public String getCustomerTourDetail() {
        return "c-tour-detail";
    }

    @GetMapping("/chi-tiet-tin-tuc")
    public String getCustomerNewsDetail() {
        return "c-news-detail";
    }

}
