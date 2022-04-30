package com.ou.common.services;

import com.ou.pojos.ScheduleEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface CMScheduleService {
    JSONArray getSchedules(Integer pageIndex);

    ScheduleEntity getScheduleAsObj(String scheSlug);

    JSONObject getScheduleAsJsonObj(String scheSlug);

    List<ScheduleEntity> getSchedulesByTourId(Integer tourId);

    boolean createSchedule(ScheduleEntity scheduleEntity);

    boolean updateSchedule(ScheduleEntity scheduleEntity);

    boolean deleteSchedule(ScheduleEntity scheduleEntity);
}
