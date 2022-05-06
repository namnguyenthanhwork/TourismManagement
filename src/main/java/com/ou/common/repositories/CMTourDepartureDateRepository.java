package com.ou.common.repositories;



import com.ou.pojos.TourDepartureDateEntity;

import java.util.List;

public interface CMTourDepartureDateRepository {
    List<TourDepartureDateEntity> getTourDepartureDateByTour(String tourSlug);
    List<TourDepartureDateEntity> getTourDepartureDateByDepartureDate(Integer dptId);

    TourDepartureDateEntity getTourDepartureDateEntity(Integer tourId, Integer dptId);
    boolean createTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);

    boolean updateTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);

    boolean deleteTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);
}
