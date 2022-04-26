package com.ou.common.repositories;



import com.ou.pojos.TourServiceEntity;

import java.util.List;

public interface CMTourServiceRepository {
    List<TourServiceEntity> getTourServiceByTour(String tourSlug);
    List<TourServiceEntity> getTourServiceByService(String servSlug);

    boolean createTourService(TourServiceEntity tourServiceEntity);

    boolean updateTourService(TourServiceEntity tourServiceEntity);

    boolean deleteTourService(TourServiceEntity tourServiceEntity);
}
