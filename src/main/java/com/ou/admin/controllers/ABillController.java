package com.ou.admin.controllers;

import com.ou.common.services.CMBillService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.utils.PageUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/hoa-don")
public class ABillController {

    @Autowired
    private CMBillService cMBillService;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getBillsView() {
        return "a-bill";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getBillsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray bills = cMBillService.getBills(pageIndex);
        return new ResponseEntity<>(bills, bills.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{billId}")
    public String getBillDetailView(@PathVariable Integer billId) {
        return "a-bill-detail";
    }

    @GetMapping("/{billId}/chi-tiet")
    public ResponseEntity<JSONObject> getAccountDetail(@PathVariable Integer billId) {
        JSONObject bill = cMBillService.getBillAsJson(billId);
        if (bill == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getBillPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMBillService.getBillAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

}
