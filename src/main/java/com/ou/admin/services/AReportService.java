package com.ou.admin.services;

import com.ou.utils.TimeUtil;
import org.json.simple.JSONArray;

public interface AReportService {
    JSONArray reportRevenue(TimeUtil timeUtil, Integer pageIndex, Integer... times);
    JSONArray reportTourAmount(TimeUtil timeUtil, Integer pageIndex, Integer... times);
}
