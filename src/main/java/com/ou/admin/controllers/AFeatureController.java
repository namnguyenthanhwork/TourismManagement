package com.ou.admin.controllers;

import com.ou.common.services.CMFeatureService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.FeatureEntity;
import com.ou.utils.PageUtil;
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
@RequestMapping(path = "/quan-tri-vien/dac-diem-ngay-khoi-hanh")
public class AFeatureController {

    @Autowired
    private CMFeatureService cMFeatureService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getFeaturesView() {
        return "a-feature";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getFeaturesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray features = cMFeatureService.getFeatures(pageIndex);
        return new ResponseEntity<>(features, features.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getFeaturePageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMFeatureService.getFeatureAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // create
    @GetMapping("/tao-moi")
    public String getFeatureCreatedView() {
        return "a-feature-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createFeature(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        FeatureEntity featureEntity = pojoBeanFactory.getApplicationContext().getBean(FeatureEntity.class);
        featureEntity.setFeaName(httpServletRequest.getParameter("feaName"));
        boolean createdResult = cMFeatureService.createFeature(featureEntity);
        if (createdResult)
            return "redirect:/quan-tri-vien/dac-diem-ngay-khoi-hanh";
        return "redirect:/quan-tri-vien/dac-diem-ngay-khoi-hanh/tao-moi";
    }

    // update
    @GetMapping("/{feaSlug}")
    public String getFeatureEditedView(@PathVariable String feaSlug) {
        return "a-feature-updated";
    }

    @GetMapping("/{feaSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getFeatureDetail(@PathVariable String feaSlug) {
        JSONObject feature = cMFeatureService.getFeatureAsJsonObj(feaSlug);
        if (feature == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(feature, HttpStatus.OK);
    }

    @RequestMapping(value = "/{feaSlug}", method = RequestMethod.POST)
    public String updateFeature(@PathVariable String feaSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(feaSlug);
        if (feature == null)
            return String.format("redirect:/quan-tri-vien/dac-diem-ngay-khoi-hanh/%s",feaSlug);
        feature.setFeaName(httpServletRequest.getParameter("feaName"));
        boolean updateResult = cMFeatureService.updateFeature(feature);
        if (updateResult)
            return "redirect:/quan-tri-vien/dac-diem-ngay-khoi-hanh";
        return String.format("redirect:/quan-tri-vien/dac-diem-ngay-khoi-hanh/%s",feaSlug);
    }

    // delete
    @RequestMapping(value = "/{feaSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteFeature(@PathVariable String feaSlug) {
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(feaSlug);
        if (feature == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMFeatureService.deleteFeature(feature);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
