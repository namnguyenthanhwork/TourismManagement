package com.ou.common.services.impl;

import com.ou.common.repositories.CMDepartureDateRepository;
import com.ou.common.repositories.CMFeatureRepository;
import com.ou.common.services.CMDepartureDateService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.FeatureEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMDepartureDateServiceImpl implements CMDepartureDateService {
    @Autowired
    private CMDepartureDateRepository cMDepartureDateRepository;

    @Autowired
    private CMFeatureRepository cMFeatureRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getDepartureDats(Integer pageIndex) {
        List<Object[]> departureDates = cMDepartureDateRepository.getDepartureDates(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        departureDates.forEach(departureDate -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("dptId", departureDate[0]);
            jsonObject.put("dptDate", departureDate[1]);
            jsonObject.put("feaId", departureDate[2]);
            jsonObject.put("feaName", departureDate[3]);
            jsonObject.put("feaSlug", departureDate[4]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public DepartureDateEntity getDepartureDatAsObj(Integer dptId) {
        if (dptId == null)
            return null;
        return cMDepartureDateRepository.getDepartureDate(dptId);
    }

    @Override
    public JSONObject getDepartureDatAsJsonObj(Integer dptId) {
        if (dptId == null)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        DepartureDateEntity departureDate = cMDepartureDateRepository.getDepartureDate(dptId);
        if (departureDate == null)
            return null;
        jsonObject.put("dptId", departureDate.getDptId());
        jsonObject.put("dptDate", departureDate.getDptDate());
        FeatureEntity featureEntity = cMFeatureRepository.getFeature(departureDate.getFeaId());
        jsonObject.put("feaId", featureEntity.getFeaId());
        jsonObject.put("feaName", featureEntity.getFeaName());
        jsonObject.put("feaSlug", featureEntity.getFeaSlug());
        return jsonObject;
    }

    @Override
    public boolean createDepartureDat(DepartureDateEntity departureDateEntity) {
        return cMDepartureDateRepository.createDepartureDate(departureDateEntity);
    }

    @Override
    public boolean updateDepartureDat(DepartureDateEntity departureDateEntity) {
        return cMDepartureDateRepository.updateDepartureDate(departureDateEntity);
    }

    @Override
    public boolean deleteDepartureDat(DepartureDateEntity departureDateEntity) {
        return cMDepartureDateRepository.deleteDepartureDate(departureDateEntity);
    }
}
