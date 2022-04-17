package com.ou.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(path = "/")
    public String test() {

        return "a-base";
    }
}
