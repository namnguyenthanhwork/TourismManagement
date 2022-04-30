package com.ou.admin.controllers;


import com.ou.common.services.CMBillService;
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

    // get
    @GetMapping()
    public String getPaymentStaffView() {
        return "s-payment";
    }

    @PostMapping()
    public ResponseEntity<JSONObject> getPaymentDetail(@RequestBody Map<String, String> body) {
        Integer billId = Integer.valueOf(body.get("billId"));
        JSONObject billInfo = cMBillService.getBillAsJson(billId);
        return new ResponseEntity<>(billInfo, billInfo != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping("/thanh-toan/cap-nhat")
    public void updatePaymentStaff(@RequestBody Map<String, String> body) {
        Integer billId = Integer.valueOf(body.get("billId"));
        BillEntity bill = cMBillService.getBillAsObj(billId);
        bill.setBillIsPaid(true);
        cMBillService.updateBill(bill);
    }
}
