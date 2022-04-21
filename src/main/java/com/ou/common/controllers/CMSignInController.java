package com.ou.common.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/auth")
public class CMSignInController {

    @GetMapping("/dang-nhap")
    public String getCommonSignIn() {
        return "cm-sign-in";
    }

}
