package com.ou.admin.controllers;

import com.ou.common.services.CMCategoryService;
import com.ou.common.services.CMStorageService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.CategoryEntity;
import com.ou.pojos.StorageEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Repository
@RequestMapping(path = "/quan-tri-vien/loai-tour")
public class ACategoryController {

    @Autowired
    private CMCategoryService cMCategoryService;

    @Autowired
    private CMStorageService cMStorageService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    // get
    @GetMapping()
    public String getCategoriesView() {
        return "a-category";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getCategoriesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray categories = cMCategoryService.getCategories(pageIndex);
        return new ResponseEntity<>(categories, categories.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getCategoryCreatedView() {
        return "a-category-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createCategory(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        CategoryEntity category = pojoBeanFactory.getApplicationContext().getBean(CategoryEntity.class);
        StorageEntity storage = cMStorageService.getStorageAsObj(httpServletRequest.getParameter("storSlug"));
        category.setCatName(httpServletRequest.getParameter("catName"));
        category.setStorId(storage.getStorId());
        boolean createdResult = cMCategoryService.createCategory(category);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{catSlug}")
    public String getCategoryEditedView(@PathVariable String catSlug) {
        return "a-category-updated";
    }

    @GetMapping("/{catSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getCategoryDetail(@PathVariable String catSlug) {
        JSONObject category = cMCategoryService.getCategoryAsJsonObj(catSlug);
        if (category == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{catSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateCategory(@PathVariable String catSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        CategoryEntity category = cMCategoryService.getCategoryAsObj(catSlug);
        StorageEntity storage = cMStorageService.getStorageAsObj(httpServletRequest.getParameter("storSlug"));
        if (category == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        category.setCatName(httpServletRequest.getParameter("catName"));
        category.setStorId(storage.getStorId());
        boolean updateResult = cMCategoryService.updateCategory(category);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{catSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable String catSlug) {
        CategoryEntity category = cMCategoryService.getCategoryAsObj(catSlug);
        if (category == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMCategoryService.deleteCategory(category);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
