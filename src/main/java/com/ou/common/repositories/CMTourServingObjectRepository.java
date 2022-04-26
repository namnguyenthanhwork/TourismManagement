package com.ou.common.repositories;


import com.ou.pojos.TourServingObjectEntity;

import java.util.List;

public interface CMTourServingObjectRepository {
    List<TourServingObjectEntity> getTourServingObjectByTour(String tourSlug);
    List<TourServingObjectEntity> getTourServingObjectByServingObject(String svoSlug);

    boolean createTourServingObject(TourServingObjectEntity tourServingObjectEntity);

    boolean updateTourServingObject(TourServingObjectEntity tourServingObjectEntity);

    boolean deleteTourServingObject(TourServingObjectEntity tourServingObjectEntity);
}
