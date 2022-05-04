package com.ou.admin.controllers;

import com.ou.admin.services.AReportService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.utils.TimeUtil;
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
@RequestMapping(path = "/quan-tri-vien/bao-cao")
public class AReportController {
    @Autowired
    private AReportService aReportService;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getReportView() {
        return "a-report";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getReportInfo(@RequestParam Map<String, String> params) {
        String typeParam = params.get("loai");
        String timeParam = params.get("thoi-gian");
        String fTime = params.get("tg1");
        String sTime = params.get("tg2");
        TimeUtil timeUtil;
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        Integer fT = null;
        Integer sT = null;
        try {
            fT = Integer.parseInt(fTime);
        } catch (NumberFormatException ignored) {
        }

        try {
            sT = Integer.parseInt(sTime);
        } catch (NumberFormatException ignored) {
        }
        JSONArray reportInfos = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        if (typeParam == null)
            reportInfos = aReportService.reportRevenue(TimeUtil.MONTH, pageIndex, fT, sT);
        else {
            switch (timeParam.toLowerCase().trim()) {
                case "thang" -> timeUtil = TimeUtil.MONTH;
                case "quy" -> timeUtil = TimeUtil.QUARTER;
                case "nam" -> timeUtil = TimeUtil.YEAR;
                default -> {
                    return new ResponseEntity<>(reportInfos, HttpStatus.NO_CONTENT);
                }
            }
            switch (typeParam.toLowerCase().trim()) {
                case "doanh-thu" -> reportInfos = aReportService.reportRevenue(timeUtil, pageIndex, fT, sT);
                case "so-luong-tour" -> reportInfos = aReportService.reportTourAmount(timeUtil, pageIndex, fT, sT);
                default -> {
                    return new ResponseEntity<>(reportInfos, HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(reportInfos, HttpStatus.OK);
    }
}
