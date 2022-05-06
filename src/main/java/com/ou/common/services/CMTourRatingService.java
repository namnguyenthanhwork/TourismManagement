package com.ou.common.services;

import com.ou.pojos.TourRatingEntity;
import org.json.simple.JSONObject;

import java.util.List;

public interface CMTourRatingService {
    List<TourRatingEntity> getTourRatingByAccount(String accUsername);
    List<TourRatingEntity> getTourRatingByTour(String tourSlug);
    TourRatingEntity getTourRating(Integer tourId, Integer accId);
    JSONObject getTourRatingAmount(Integer tourId);

    int getTotalTourRatingAmount(Integer tourId);
    int getTourRatingRecordAmount(Integer tourId);
    boolean createTourRating(TourRatingEntity tourRatingEntity);

    boolean updateTourRating(TourRatingEntity tourRatingEntity);

    boolean deleteTourRating(TourRatingEntity tourRatingEntity);
}
