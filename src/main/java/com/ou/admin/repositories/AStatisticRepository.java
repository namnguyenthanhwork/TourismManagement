package com.ou.admin.repositories;

import com.ou.utils.TimeUtil;

import java.util.List;

public interface AStatisticRepository {
    List<Object[]> statisticRevenue(TimeUtil timeUtil, Integer... times);
    List<Object[]> statisticTourAmount(TimeUtil timeUtil,Integer... times);
}
