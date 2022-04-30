package com.ou.customer.controllers;

import com.ou.customer.services.CNavigationService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CNavigationController {
    @Autowired
    private CNavigationService cNavigationService;

    @GetMapping( "/thanh-dieu-huong")
    public ResponseEntity<JSONArray> getNavigationInfo(){
        JSONArray navInfo = cNavigationService.getNavigation();
        return new ResponseEntity<>(navInfo, HttpStatus.OK);
    }
}
