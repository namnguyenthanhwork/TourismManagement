package com.ou.admin.controllers;

import com.ou.admin.services.AStatisticService;
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
@RequestMapping(path = "/quan-tri-vien/thong-ke")
public class AStatisticController {

    @Autowired
    private AStatisticService aStatisticService;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getStatisticView() {
        return "a-statistic";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getStatisticInfo(@RequestParam Map<String, String> params) {
        String typeParam = params.get("loai");
        String timeParam = params.get("thoi-gian");
        String fromTimeParam = params.get("bat-dau");
        String toTimeParam = params.get("ket-thuc");
        TimeUtil timeUtil;
        JSONArray statisticInfos = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        if (typeParam == null)
            statisticInfos = aStatisticService.statisticRevenue(TimeUtil.MONTH,
                    Integer.parseInt(fromTimeParam), Integer.parseInt(toTimeParam));
        else {
            switch (timeParam.toLowerCase().trim()) {
                case "thang" -> timeUtil = TimeUtil.MONTH;
                case "quy" -> timeUtil = TimeUtil.QUARTER;
                case "nam" -> timeUtil = TimeUtil.YEAR;
                case "thang-den-thang" -> timeUtil = TimeUtil.MONTH_TO_MONTH;
                case "quy-den-quy" -> timeUtil = TimeUtil.QUARTER_TO_QUARTER;
                case "nam-den-nam" -> timeUtil = TimeUtil.YEAR_TO_YEAR;
                default -> {
                    return new ResponseEntity<>(statisticInfos, HttpStatus.NO_CONTENT);
                }
            }
            Integer fd = null;
            Integer td = null;
            try {
                fd = Integer.parseInt(fromTimeParam);
            } catch (NumberFormatException ignored) {
            }

            try {
                td = Integer.parseInt(toTimeParam);
            } catch (NumberFormatException ignored) {
            }

            switch (typeParam.toLowerCase().trim()) {
                case "doanh-thu" -> statisticInfos = aStatisticService.statisticRevenue(timeUtil, fd, td);
                case "so-luong-tour" -> statisticInfos = aStatisticService.statisticTourAmount(timeUtil, fd, td);
                default -> {
                    return new ResponseEntity<>(statisticInfos, HttpStatus.NO_CONTENT);
                }

            }
        }
        return new ResponseEntity<>(statisticInfos, statisticInfos.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
