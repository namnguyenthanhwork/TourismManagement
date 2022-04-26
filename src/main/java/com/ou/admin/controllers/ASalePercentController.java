package com.ou.admin.controllers;


import com.ou.common.services.CMSalePercentService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SalePercentEntity;
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
@RequestMapping(path = "/quan-tri-vien/phan-tram-giam-gia")
public class ASalePercentController {
//
    @Autowired
    private CMSalePercentService cMSalePercentService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getSalePercentsView() {
        return "a-sale-percent";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getSalePercentsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray salePercents = cMSalePercentService.getSalePercents(pageIndex);
        return new ResponseEntity<>(salePercents, salePercents.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getSalePercentCreatedView() {
        return "a-sale-percent-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createSalePercent(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SalePercentEntity salePercentEntity = pojoBeanFactory.getApplicationContext().getBean(SalePercentEntity.class);
        salePercentEntity.setSperPercent(Integer.valueOf(httpServletRequest.getParameter("sperPercent")));
        boolean createdResult = cMSalePercentService.createSalePercent(salePercentEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{sperId}")
    public String getSalePercentEditedView(@PathVariable Integer sperId) {
        return "a-sale-percent-updated";
    }

    @GetMapping("/{sperId}/chinh-sua")
    public ResponseEntity<JSONObject> getSalePercentDetail(@PathVariable Integer sperId) {
        JSONObject salePercent = cMSalePercentService.getSalePercentAsJsonObj(sperId);
        if (salePercent == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(salePercent, HttpStatus.OK);
    }

    @RequestMapping(value = "/{sperId}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateSalePercent(@PathVariable Integer sperId,
                                                        HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(sperId);
        if (salePercent == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        salePercent.setSperPercent(Integer.valueOf(httpServletRequest.getParameter("sperPercent")));
        boolean updateResult = cMSalePercentService.updateSalePercent(salePercent);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{sperId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSalePercent(@PathVariable Integer sperId) {
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(sperId);
        if (salePercent == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMSalePercentService.deleteSalePercent(salePercent);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
