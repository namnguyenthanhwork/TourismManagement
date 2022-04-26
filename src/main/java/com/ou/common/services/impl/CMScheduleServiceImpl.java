package com.ou.common.services.impl;


import com.ou.common.repositories.CMScheduleRepository;
import com.ou.common.services.CMScheduleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ScheduleEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMScheduleServiceImpl implements CMScheduleService {

    @Autowired
    private CMScheduleRepository cMScheduleRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getSchedules(Integer pageIndex) {
        List<Object[]> schedules = cMScheduleRepository.getSchedules(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        schedules.forEach(schedule -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("scheId", schedule[0]);
            jsonObject.put("scheTitle", schedule[1]);
            jsonObject.put("scheSlug", schedule[2]);
            jsonObject.put("scheContent", schedule[3]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public ScheduleEntity getScheduleAsObj(String scheSlug) {
        if (scheSlug == null || scheSlug.trim().length() == 0)
            return null;
        return cMScheduleRepository.getSchedule(scheSlug);
    }

    @Override
    public JSONObject getScheduleAsJsonObj(String scheSlug) {
        if (scheSlug == null || scheSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        ScheduleEntity schedule = cMScheduleRepository.getSchedule(scheSlug.trim());
        if (schedule == null)
            return null;
        jsonObject.put("scheId", schedule.getScheId());
        jsonObject.put("scheTitle", schedule.getScheTitle());
        jsonObject.put("scheSlug", schedule.getScheSlug());
        jsonObject.put("scheContent", schedule.getScheContent());
        return jsonObject;
    }

    @Override
    public boolean createSchedule(ScheduleEntity scheduleEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(scheduleEntity.getScheTitle());
        scheduleEntity.setScheSlug(slugUtil.getSlug());
        return cMScheduleRepository.createSchedule(scheduleEntity);
    }

    @Override
    public boolean updateSchedule(ScheduleEntity scheduleEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(scheduleEntity.getScheTitle());
        scheduleEntity.setScheSlug(slugUtil.getSlug());
        return cMScheduleRepository.updateSchedule(scheduleEntity);
    }

    @Override
    public boolean deleteSchedule(ScheduleEntity scheduleEntity) {
        return cMScheduleRepository.deleteSchedule(scheduleEntity);
    }
}
