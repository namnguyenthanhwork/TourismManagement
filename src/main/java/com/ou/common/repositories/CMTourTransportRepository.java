package com.ou.common.repositories;


import com.ou.pojos.TourTransportEntity;

import java.util.List;

public interface CMTourTransportRepository {
    List<TourTransportEntity> getTourTransportByTour(String tourSlug);
    List<TourTransportEntity> getTourTransportByTransport(String tranSlug);

    boolean createTourTransport(TourTransportEntity tourTransportEntity);

    boolean updateTourTransport(TourTransportEntity tourTransportEntity);

    boolean deleteTourTransport(TourTransportEntity tourTransportEntity);
}
