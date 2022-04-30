package com.ou.admin.controllers;

import com.ou.common.services.CMBillService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/hoa-don")
public class ABillController {

    @Autowired
    private CMBillService cMBillService;

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

}
