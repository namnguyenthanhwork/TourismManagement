package com.ou.common.repositories;


import com.ou.pojos.TourEntity;

import java.util.List;

public interface CMTourRepository {
    List<Object[]> getTours(Integer pageIndex, String ... params);
    List<Object[]> getTours();
    long getTourAmount();

    TourEntity getTour(String tourSlug);

    TourEntity getTour(Integer tourId);

    boolean createTour(TourEntity tourEntity);

    boolean updateTour(TourEntity tourEntity);

    boolean deleteTour(TourEntity tourEntity);
}
