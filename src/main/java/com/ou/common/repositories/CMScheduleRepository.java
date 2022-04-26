package com.ou.common.repositories;


import com.ou.pojos.ScheduleEntity;

import java.util.List;

public interface CMScheduleRepository {
    List<Object[]> getSchedules(Integer pageIndex);

    ScheduleEntity getSchedule(String scheSlug);

    ScheduleEntity getSchedule(Integer scheId);

    boolean createSchedule(ScheduleEntity scheduleEntity);

    boolean updateSchedule(ScheduleEntity scheduleEntity);

    boolean deleteSchedule(ScheduleEntity scheduleEntity);
}
