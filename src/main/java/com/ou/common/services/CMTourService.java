package com.ou.common.services;

import com.ou.pojos.TourEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMTourService {
    JSONArray getTours(Integer pageIndex, String ... params);

    JSONArray getTours();
    long getTourAmount(String ... params);

    int getTotalTourSlot(Integer tourId);
    int getEmptySlotAmount(Integer tourId);

    TourEntity getTourAsObj(String tourSlug);

    JSONObject getTourAverageRating(String tourSlug);

    JSONObject getTourAsJsonObj(String tourSlug);

    boolean createTour(TourEntity tourEntity);

    boolean updateTour(TourEntity tourEntity);

    boolean deleteTour(TourEntity tourEntity);
}
