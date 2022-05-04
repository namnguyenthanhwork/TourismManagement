package com.ou.customer.services.impl;

import com.ou.common.repositories.*;
import com.ou.common.services.CMScheduleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.repositories.CHomePageRepository;
import com.ou.customer.services.CHomePageService;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.ScheduleEntity;
import com.ou.pojos.TourDepartureDateEntity;
import com.ou.pojos.TourServingObjectEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CHomePageServiceImpl implements CHomePageService {
    @Autowired
    private CHomePageRepository cHomePageRepository;

    @Autowired
    private CMScheduleRepository cMScheduleRepository;

    @Autowired
    private CMTourDepartureDateRepository cMTourDepartureDateRepository;

    @Autowired
    private CMTourServingObjectRepository cMTourServingObjectRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getTourByCategory(String catSlug) {
        if(catSlug==null|| catSlug.trim().length()==0)
            return null;
        List<Object[]> tours = cHomePageRepository.getTourByCategory(catSlug.trim());
        JSONArray jsonArray= utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tours.forEach(tour->{
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("tourTitle", tour[0]);
            jsonObject.put("tourSlug", tour[1]);
            jsonObject.put("tourCoverPage", tour[2]);
            int tourId = (int) tour[3];
            List<ScheduleEntity> schedules = cMScheduleRepository.getSchedulesByTourId(tourId);
            List<TourServingObjectEntity> tourServingObjects =
                    cMTourServingObjectRepository.getTourServingObjectByTour((String) tour[1]);
            List<TourDepartureDateEntity> tourDepartureDates =
                    cMTourDepartureDateRepository.getTourDepartureDateByTour((String) tour[1]);
//            jsonObject.put("")
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }
}
