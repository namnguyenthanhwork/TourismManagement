package com.ou.common.services;

import com.ou.pojos.TourTransportEntity;

import java.util.List;

public interface CMTourTransportService {
    List<TourTransportEntity> getTourTransportByTour(String tourSlug);
    List<TourTransportEntity> getTourTransportByTransport(String tranSlug);

    boolean createTourTransport(TourTransportEntity tourTransportEntity);

    boolean updateTourTransport(TourTransportEntity tourTransportEntity);

    boolean deleteTourTransport(TourTransportEntity tourTransportEntity);
}
