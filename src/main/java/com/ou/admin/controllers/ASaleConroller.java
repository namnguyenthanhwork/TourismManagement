package com.ou.admin.controllers;

import com.ou.common.services.CMSalePercentService;
import com.ou.common.services.CMSaleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SaleEntity;
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
import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/giam-gia")
public class ASaleConroller {

    @Autowired
    private CMSaleService cMSaleService;

    @Autowired
    private CMSalePercentService cMSalePercentService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    // get
    @GetMapping()
    public String getSalesView() {
        return "a-sale";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getSalesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray sales = cMSaleService.getSales(pageIndex);
        return new ResponseEntity<>(sales, sales.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getSaleCreatedView() {
        return "a-sale-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createSale(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SaleEntity sale = pojoBeanFactory.getApplicationContext().getBean(SaleEntity.class);
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(
                Integer.valueOf(httpServletRequest.getParameter("sperId")));
        sale.setSaleFromDate(Timestamp.valueOf(httpServletRequest.getParameter("saleFromDate")));
        sale.setSaleToDate(Timestamp.valueOf(httpServletRequest.getParameter("saleToDate")));
        sale.setSperId(salePercent.getSperId());
        boolean createdResult = cMSaleService.createSale(sale);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{saleId}")
    public String getSaleEditedView(@PathVariable Integer saleId) {
        return "a-sale-updated";
    }

    @GetMapping("/{saleId}/chinh-sua")
    public ResponseEntity<JSONObject> getSaleDetail(@PathVariable Integer saleId) {
        JSONObject sale = cMSaleService.getSaleAsJsonObj(saleId);
        if (sale == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @RequestMapping(value = "/{saleId}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateSale(@PathVariable Integer saleId, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SaleEntity sale = cMSaleService.getSaleAsObj(saleId);
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(
                Integer.valueOf(httpServletRequest.getParameter("sperId")));
        if (sale == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        sale.setSaleFromDate(Timestamp.valueOf(httpServletRequest.getParameter("saleFromDate")));
        sale.setSaleToDate(Timestamp.valueOf(httpServletRequest.getParameter("saleToDate")));
        sale.setSperId(salePercent.getSperId());
        boolean updateResult = cMSaleService.updateSale(sale);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{saleId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSale(@PathVariable Integer saleId) {
        SaleEntity sale = cMSaleService.getSaleAsObj(saleId);
        if (sale == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMSaleService.deleteSale(sale);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
