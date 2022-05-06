package com.ou.common.repositories;


import com.ou.pojos.TourEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CMTourRepository {
    List<Object[]> getTours(Integer pageIndex, String ... params);
    List<Object[]> getTours();
    long getTourAmount();

    long getTotalTourSlot(Integer tourId, Timestamp timestamp);
    long getTotalSellTourSlot(Integer tourId, Timestamp timestamp);

    TourEntity getTour(String tourSlug);

    TourEntity getTour(Integer tourId);

    boolean createTour(TourEntity tourEntity);

    boolean updateTour(TourEntity tourEntity);

    boolean deleteTour(TourEntity tourEntity);
}
