package com.ou.admin.services.impl;

import com.ou.admin.repositories.AStatisticRepository;
import com.ou.admin.services.AStatisticService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.utils.TimeUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AStatisticServiceImpl implements AStatisticService {

    @Autowired
    private AStatisticRepository aStatisticRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;
    @Override
    public JSONArray statisticRevenue(TimeUtil timeUtil, Integer... times) {
        List<Object[]> revenueInfos  = aStatisticRepository.statisticRevenue(timeUtil, times);
        JSONArray jsonArray=utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        revenueInfos.forEach(revenueInfo->{
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("time", revenueInfo[0]);
            jsonObject.put("billTotalMoney", revenueInfo[1]);
            jsonObject.put("billTotalSaleMoney", revenueInfo[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public JSONArray statisticTourAmount(TimeUtil timeUtil, Integer... times) {
        List<Object[]> tourAmountInfos  = aStatisticRepository.statisticTourAmount(timeUtil, times);
        JSONArray jsonArray=utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tourAmountInfos.forEach(tourAmountInfo->{
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("time", tourAmountInfo[0]);
            jsonObject.put("tourAmount", tourAmountInfo[1]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }
}
