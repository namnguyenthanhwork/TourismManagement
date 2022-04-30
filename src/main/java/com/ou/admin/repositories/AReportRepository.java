package com.ou.admin.repositories;

import com.ou.utils.TimeUtil;

import java.util.List;

public interface AReportRepository {
    List<Object[]> reportRevenue(TimeUtil timeUtil, Integer pageIndex, Integer... times);
    List<Object[]> reportTourAmount(TimeUtil timeUtil, Integer pageIndex, Integer... times);
}
