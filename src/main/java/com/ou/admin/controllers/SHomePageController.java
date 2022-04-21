package com.ou.admin.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/nhan-vien")
public class SHomePageController {

    @GetMapping("/trang-chu")
    public String getStaffHomePage(){
        return "s-homepage";
    }
}
