package com.ou.common.services;

import com.ou.pojos.TourDepartureDateEntity;

import java.util.List;

public interface CMTourDepartureDateService {
    List<TourDepartureDateEntity> getTourDepartureDateByTour(String tourSlug);
    List<TourDepartureDateEntity> getTourDepartureDateByDepartureDate(Integer dptId);

    boolean createTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);

    boolean updateTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);

    boolean deleteTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity);
}
