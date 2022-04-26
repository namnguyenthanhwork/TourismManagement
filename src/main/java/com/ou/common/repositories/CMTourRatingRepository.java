package com.ou.common.repositories;



import com.ou.pojos.TourRatingEntity;

import java.util.List;

public interface CMTourRatingRepository {
    List<TourRatingEntity> getTourRatingByAccount(String accUsername);
    List<TourRatingEntity> getTourRatingByTour(String tourSlug);

    boolean createTourRating(TourRatingEntity tourRatingEntity);

    boolean updateTourRating(TourRatingEntity tourRatingEntity);

    boolean deleteTourRating(TourRatingEntity tourRatingEntity);
}
