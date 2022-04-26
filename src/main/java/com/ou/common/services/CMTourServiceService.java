package com.ou.common.services;



import com.ou.pojos.TourServiceEntity;

import java.util.List;

public interface CMTourServiceService {
    List<TourServiceEntity> getTourServiceByTour(String tourSlug);
    List<TourServiceEntity> getTourServiceByService(String servSlug);

    boolean createTourService(TourServiceEntity tourServiceEntity);

    boolean updateTourService(TourServiceEntity tourServiceEntity);

    boolean deleteTourService(TourServiceEntity tourServiceEntity);
}
