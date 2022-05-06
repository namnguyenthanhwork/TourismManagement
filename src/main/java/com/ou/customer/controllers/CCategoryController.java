package com.ou.customer.controllers;


import com.ou.common.services.CMCategoryService;
import com.ou.customer.services.CHomePageService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loai-tour")
public class CCategoryController {

    @Autowired
    private CMCategoryService cMCategoryService;
    @Autowired
    private CHomePageService cHomePageService;

    @GetMapping("/loai-tour/thong-tin")
    public ResponseEntity<JSONArray> getCategoriesInfosCustomerHomePage() {
        JSONArray categories = cMCategoryService.getCategories(null);
        return new ResponseEntity<>(categories, categories.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{catSlug}")
    public ResponseEntity<JSONArray> getTourGeneralInfoByCategory(@PathVariable String catSlug) {
        JSONArray tours = cHomePageService.getTourByCategory(catSlug);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }
}
