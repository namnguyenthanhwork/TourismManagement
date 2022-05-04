package com.ou.admin.controllers;


import com.ou.common.services.CMBillService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.BillEntity;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/nhan-vien/thanh-toan")
public class SPaymentController {
    @Autowired
    private CMBillService cMBillService;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;
    // get
    @GetMapping()
    public String getPaymentStaffView() {
        return "s-payment";
    }

    @PostMapping()
    public ResponseEntity<JSONObject> getPaymentDetail(@RequestBody Map<String, String> body) {
        try {
            Integer billId = Integer.valueOf(body.get("billId"));
            JSONObject billInfo = cMBillService.getBillAsJson(billId);
            return new ResponseEntity<>(billInfo == null?
                    utilBeanFactory.getApplicationContext().getBean(JSONObject.class):billInfo,
                    HttpStatus.OK);
        }catch (NumberFormatException numberFormatException){
            return new ResponseEntity<>(  utilBeanFactory.getApplicationContext().getBean(JSONObject.class), HttpStatus.OK);
        }

    }

    @PostMapping("/cap-nhat")
    public ResponseEntity<HttpStatus> updatePaymentStaff(@RequestBody Map<String, String> body) {
        Integer billId = Integer.valueOf(body.get("billId"));
        BillEntity bill = cMBillService.getBillAsObj(billId);
        bill.setBillIsPaid(true);
        boolean result = cMBillService.updateBill(bill);
        return  new ResponseEntity<>(result?HttpStatus.OK:HttpStatus.CONFLICT);
    }
}
