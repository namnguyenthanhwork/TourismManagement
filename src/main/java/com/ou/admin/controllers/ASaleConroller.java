package com.ou.admin.controllers;

import com.ou.common.services.CMSalePercentService;
import com.ou.common.services.CMSaleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SaleEntity;
import com.ou.pojos.SalePercentEntity;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray sales = cMSaleService.getSales(pageIndex);
        return new ResponseEntity<>(sales, sales.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getSalePageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMSaleService.getSaleAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // create
    @GetMapping("/tao-moi")
    public String getSaleCreatedView() {
        return "a-sale-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createSale(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SaleEntity sale = pojoBeanFactory.getApplicationContext().getBean(SaleEntity.class);
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(
                Integer.valueOf(httpServletRequest.getParameter("sperId")));
        Timestamp saleFromDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        Timestamp saleToDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        saleFromDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(httpServletRequest
                .getParameter("saleFromDate") + " 00:00:00").getTime());
        saleToDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(httpServletRequest
                .getParameter("saleToDate") + " 00:00:00").getTime());
        sale.setSaleFromDate(saleFromDate);
        sale.setSaleToDate(saleToDate);
        sale.setSperId(salePercent.getSperId());
        boolean createdResult = cMSaleService.createSale(sale);
        if (createdResult)
            return "redirect:/quan-tri-vien/giam-gia";
        return "redirect:/quan-tri-vien/giam-gia/tao-moi?error=1";
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
    public String updateSale(@PathVariable Integer saleId, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SaleEntity sale = cMSaleService.getSaleAsObj(saleId);
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(
                Integer.valueOf(httpServletRequest.getParameter("sperId")));
        if (sale == null)
            return String.format("redirect:/quan-tri-vien/giam-gia/%d?error=1", saleId);
        Timestamp saleFromDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        Timestamp saleToDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        saleFromDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(httpServletRequest
                .getParameter("saleFromDate") + " 00:00:00").getTime());
        saleToDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(httpServletRequest
                .getParameter("saleToDate") + " 00:00:00").getTime());
        sale.setSaleFromDate(saleFromDate);
        sale.setSaleToDate(saleToDate);
        sale.setSperId(salePercent.getSperId());
        boolean updateResult = cMSaleService.updateSale(sale);
        if (updateResult)
            return "redirect:/quan-tri-vien/giam-gia";
        return String.format("redirect:/quan-tri-vien/giam-gia/%d?error=1", saleId);
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
