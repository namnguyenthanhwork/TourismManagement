package com.ou.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/quan-tri-vien")
public class AHomePageController {
    @GetMapping("/trang-chu")
    public String getAdminHomePage() {
        return "a-homepage";
    }
}
