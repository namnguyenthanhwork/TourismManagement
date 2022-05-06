package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourRatingRepository;
import com.ou.common.services.CMTourRatingService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.TourRatingEntity;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourRatingServiceImpl implements CMTourRatingService {
    @Autowired
    private CMTourRatingRepository cMTourRatingRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<TourRatingEntity> getTourRatingByAccount(String accUsername) {
        if (accUsername == null || accUsername.trim().length() == 0)
            return null;
        return cMTourRatingRepository.getTourRatingByAccount(accUsername.trim());
    }

    @Override
    public List<TourRatingEntity> getTourRatingByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourRatingRepository.getTourRatingByTour(tourSlug.trim());
    }

    @Override
    public TourRatingEntity getTourRating(Integer tourId, Integer accId) {
        if (tourId == null || accId == null)
            return null;
        return cMTourRatingRepository.getTourRating(tourId, accId);
    }

    @Override
    public JSONObject getTourRatingAmount(Integer tourId) {
        if(tourId==null)
            return null;
        JSONObject jsonObject= utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        jsonObject.put("fiveStars", cMTourRatingRepository.getTourRatingAmount(tourId, 5));
        jsonObject.put("fourStars", cMTourRatingRepository.getTourRatingAmount(tourId, 4));
        jsonObject.put("threeStars", cMTourRatingRepository.getTourRatingAmount(tourId, 3));
        jsonObject.put("twoStars", cMTourRatingRepository.getTourRatingAmount(tourId, 2));
        jsonObject.put("oneStar", cMTourRatingRepository.getTourRatingAmount(tourId, 1));
        jsonObject.put("total", cMTourRatingRepository.getTourRatingAmount(tourId));
        return jsonObject;
    }

    @Override
    public int getTotalTourRatingAmount(Integer tourId) {
        if(tourId==null)
            return 0;
        return cMTourRatingRepository.getTotalTourRatingAmount(tourId);
    }

    @Override
    public int getTourRatingRecordAmount(Integer tourId) {
        if(tourId==null)
            return 0;
        return cMTourRatingRepository.getTourRatingAmount(tourId);
    }

    @Override
    public boolean createTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.createTourRating(tourRatingEntity);
    }

    @Override
    public boolean updateTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.updateTourRating(tourRatingEntity);
    }

    @Override
    public boolean deleteTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.deleteTourRating(tourRatingEntity);
    }
}
