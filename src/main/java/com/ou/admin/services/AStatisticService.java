package com.ou.admin.services;

import com.ou.utils.TimeUtil;
import org.json.simple.JSONArray;

public interface AStatisticService {
    JSONArray statisticRevenue(TimeUtil timeUtil, Integer... times);
    JSONArray statisticTourAmount(TimeUtil timeUtil,Integer... times);

}
