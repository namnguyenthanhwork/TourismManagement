package com.ou.admin.services.impl;

import com.ou.admin.repositories.AReportRepository;
import com.ou.admin.services.AReportService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.utils.TimeUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AReportServiceImpl implements AReportService {
    @Autowired
    private AReportRepository aReportRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray reportRevenue(TimeUtil timeUtil, Integer pageIndex, Integer... times) {
        List<Object[]> revenueInfos = aReportRepository.reportRevenue(timeUtil, pageIndex, times);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        revenueInfos.forEach(revenueInfo -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("billCreatedDate", revenueInfo[0]);
            jsonObject.put("billTotalMoney", revenueInfo[1]);
            jsonObject.put("billTotalSaleMoney", revenueInfo[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public JSONArray reportTourAmount(TimeUtil timeUtil, Integer pageIndex, Integer... times) {
        List<Object[]> tourAmountInfos = aReportRepository.reportTourAmount(timeUtil, pageIndex, times);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tourAmountInfos.forEach(tourAmountInfo -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("billCreatedDate", tourAmountInfo[0]);
            jsonObject.put("tourAmount", tourAmountInfo[1]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }
}
