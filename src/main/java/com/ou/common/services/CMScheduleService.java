package com.ou.common.services;

import com.ou.pojos.ScheduleEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMScheduleService {
    JSONArray getSchedules(Integer pageIndex);

    ScheduleEntity getScheduleAsObj(String scheSlug);

    JSONObject getScheduleAsJsonObj(String scheSlug);

    boolean createSchedule(ScheduleEntity scheduleEntity);

    boolean updateSchedule(ScheduleEntity scheduleEntity);

    boolean deleteSchedule(ScheduleEntity scheduleEntity);
}
