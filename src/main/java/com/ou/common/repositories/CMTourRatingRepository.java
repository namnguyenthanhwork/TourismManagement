package com.ou.common.repositories;



import com.ou.pojos.TourRatingEntity;

import java.util.List;

public interface CMTourRatingRepository {
    List<TourRatingEntity> getTourRatingByAccount(String accUsername);
    List<TourRatingEntity> getTourRatingByTour(String tourSlug);

    int getTourRatingAmount(Integer tourId, Integer ratingAmount);

    TourRatingEntity getTourRating(Integer tourId, Integer accId);

    int getTourRatingAmount(Integer tourId);

    int getTotalTourRatingAmount(Integer tourId);

    boolean createTourRating(TourRatingEntity tourRatingEntity);

    boolean updateTourRating(TourRatingEntity tourRatingEntity);

    boolean deleteTourRating(TourRatingEntity tourRatingEntity);
}
