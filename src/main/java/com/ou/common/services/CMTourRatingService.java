package com.ou.common.services;

import com.ou.pojos.TourRatingEntity;

import java.util.List;

public interface CMTourRatingService {
    List<TourRatingEntity> getTourRatingByAccount(String accUsername);
    List<TourRatingEntity> getTourRatingByTour(String tourSlug);

    boolean createTourRating(TourRatingEntity tourRatingEntity);

    boolean updateTourRating(TourRatingEntity tourRatingEntity);

    boolean deleteTourRating(TourRatingEntity tourRatingEntity);
}
