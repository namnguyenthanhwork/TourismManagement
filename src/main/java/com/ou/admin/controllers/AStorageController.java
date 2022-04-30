package com.ou.admin.controllers;


import com.ou.common.services.CMStorageService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.StorageEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/kho-chua")
public class AStorageController {

    @Autowired
    private CMStorageService cMStorageService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getStoragesView() {
        return "a-storage";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getStoragesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray storages = cMStorageService.getStorages(pageIndex);
        return new ResponseEntity<>(storages, storages.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getStorageCreatedView() {
        return "a-storage-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createStorage(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        StorageEntity storageEntity = pojoBeanFactory.getApplicationContext().getBean(StorageEntity.class);
        storageEntity.setStorName(httpServletRequest.getParameter("storName"));
        boolean createdResult = cMStorageService.createStorage(storageEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{storSlug}")
    public String getStorageEditedView(@PathVariable String storSlug) {
        return "a-storage-updated";
    }

    @GetMapping("/{storSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getStorageDetail(@PathVariable String storSlug) {
        JSONObject storage = cMStorageService.getStorageAsJsonObj(storSlug);
        if (storage == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(storage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{storSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateStorage(@PathVariable String storSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        StorageEntity storage = cMStorageService.getStorageAsObj(storSlug);
        if (storage == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        storage.setStorName(httpServletRequest.getParameter("storName"));
        boolean updateResult = cMStorageService.updateStorage(storage);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{storSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteStorage(@PathVariable String storSlug) {
        StorageEntity storage = cMStorageService.getStorageAsObj(storSlug);
        if (storage == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMStorageService.deleteStorage(storage);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
