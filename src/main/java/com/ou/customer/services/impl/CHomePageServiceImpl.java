package com.ou.customer.services.impl;

import com.ou.common.repositories.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.repositories.CHomePageRepository;
import com.ou.customer.services.CHomePageService;
import com.ou.pojos.*;
import com.ou.utils.TourQueryTypeUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
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
    private CMTourRepository cMTourRepository;

    @Autowired
    private CMDepartureDateRepository cMDepartureDateRepository;

    @Autowired
    private CMTransportRepository cMTransportRepository;

    @Autowired
    private CMTourTransportRepository cMTourTransportRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    private JSONObject getTourDetail(Object[] tour) {
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        jsonObject.put("tourTitle", tour[0]);
        jsonObject.put("tourSlug", tour[1]);
        jsonObject.put("tourCoverPage", tour[2]);
        int tourId = (int) tour[3];
        jsonObject.put("tourId", tourId);
        // schedule
        List<ScheduleEntity> schedules = cMScheduleRepository.getSchedulesByTourId(tourId);
        JSONArray scheduleJsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        schedules.forEach(scheduleEntity -> {
            JSONObject jsonObject1 = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject1.put("scheTitle", scheduleEntity.getScheTitle());
            scheduleJsonArray.add(jsonObject1);
        });
        jsonObject.put("schedules", scheduleJsonArray);

        // transport
        List<TourTransportEntity> tourTransports = cMTourTransportRepository.getTourTransportByTour((String) tour[1]);
        JSONArray transportJsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tourTransports.forEach(tourTransportEntity -> {
            JSONObject jsonObject1 = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            TransportEntity transport = cMTransportRepository.getTransport(tourTransportEntity.getTranId());
            jsonObject1.put("tranName", transport.getTranName());
            transportJsonArray.add(jsonObject1);
        });
        jsonObject.put("transports", transportJsonArray);

        // price
        List<TourServingObjectEntity> tourServingObjects =
                cMTourServingObjectRepository.getTourServingObjectByTour((String) tour[1]);
        tourServingObjects.sort(Comparator.comparing(TourServingObjectEntity::getTourPrice));
        jsonObject.put("tourPrice", tourServingObjects.size() > 0 ? tourServingObjects.get(0).getTourPrice() : null);

        //departure date
        List<TourDepartureDateEntity> tourDepartureDates =
                cMTourDepartureDateRepository.getTourDepartureDateByTour((String) tour[1]);
        Timestamp timestamp = utilBeanFactory.getApplicationContext().getBean("currentTimeStamp", Timestamp.class);
        List<DepartureDateEntity> departureDateEntities =
                pojoBeanFactory.getApplicationContext().getBean("departureDateEntities", List.class);
        tourDepartureDates.forEach(tourDepartureDateEntity -> {
            DepartureDateEntity departureDateEntity = cMDepartureDateRepository.getDepartureDate(
                    tourDepartureDateEntity.getDptId());
            if (departureDateEntity.getDptDate().after(timestamp))
                departureDateEntities.add(departureDateEntity);

        });
        departureDateEntities.sort(Comparator.comparing(DepartureDateEntity::getDptDate));
        jsonObject.put("tourDepartureDate", departureDateEntities.size() > 0 ?
                departureDateEntities.get(0).getDptDate() : null);

        // empty slot
        long totalTourSlot = cMTourRepository.getTotalTourSlot(tourId, timestamp);
        long totalTourSellSlot = cMTourRepository.getTotalSellTourSlot(tourId, timestamp);
        jsonObject.put("tourEmptySlot", (int) (totalTourSlot - totalTourSellSlot));
        return jsonObject;
    }

    private JSONArray getTourInfo(List<Object[]> tours) {

        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tours.forEach(tour -> jsonArray.add(getTourDetail(tour)));
        return jsonArray;
    }

    @Override
    public JSONArray getTourByCategory(String catSlug) {
        if (catSlug == null || catSlug.trim().length() == 0)
            return null;
        List<Object[]> tours = cHomePageRepository.getTourByCategory(catSlug.trim());
        return getTourInfo(tours);
    }

    @Override
    public JSONArray getTours(TourQueryTypeUtil tourQueryTypeUtil, Integer pageIndex, String params) {
        List<Object[]> tours = cHomePageRepository.getTours(tourQueryTypeUtil, pageIndex, params);
        return getTourInfo(tours);
    }

    @Override
    public long getTourAmount(TourQueryTypeUtil tourQueryTypeUtil, String... params) {
        return cHomePageRepository.getTourAmount(tourQueryTypeUtil, params);
    }

    @Override
    public JSONObject getTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return getTourDetail(cHomePageRepository.getTour(tourSlug));
    }
}
