package com.ou.common.services;

import com.ou.pojos.TourServingObjectEntity;

import java.util.List;

public interface CMTourServingObjectService {
    List<TourServingObjectEntity> getTourServingObjectByTour(String tourSlug);
    List<TourServingObjectEntity> getTourServingObjectByServingObject(String svoSlug);
    TourServingObjectEntity getTourServingObjectById(Integer tsvoId);
    boolean createTourServingObject(TourServingObjectEntity tourServingObjectEntity);

    boolean updateTourServingObject(TourServingObjectEntity tourServingObjectEntity);

    boolean deleteTourServingObject(TourServingObjectEntity tourServingObjectEntity);
}
