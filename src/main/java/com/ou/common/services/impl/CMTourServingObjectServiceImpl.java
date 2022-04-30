package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourServingObjectRepository;
import com.ou.common.services.CMTourServingObjectService;
import com.ou.pojos.TourServingObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourServingObjectServiceImpl implements CMTourServingObjectService {
    @Autowired
    private CMTourServingObjectRepository cMTourServingObjectRepository;

    @Override
    public List<TourServingObjectEntity> getTourServingObjectByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourServingObjectRepository.getTourServingObjectByTour(tourSlug.trim());
    }

    @Override
    public List<TourServingObjectEntity> getTourServingObjectByServingObject(String svoSlug) {
        if (svoSlug == null || svoSlug.trim().length() == 0)
            return null;
        return cMTourServingObjectRepository.getTourServingObjectByServingObject(svoSlug.trim());
    }

    @Override
    public TourServingObjectEntity getTourServingObjectById(Integer tsvoId) {
        if (tsvoId == null )
            return null;
        return cMTourServingObjectRepository.getTourServingObjectById(tsvoId);
    }

    @Override
    public boolean createTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        return cMTourServingObjectRepository.createTourServingObject(tourServingObjectEntity);
    }

    @Override
    public boolean updateTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        return cMTourServingObjectRepository.updateTourServingObject(tourServingObjectEntity);
    }

    @Override
    public boolean deleteTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        return cMTourServingObjectRepository.deleteTourServingObject(tourServingObjectEntity);
    }
}
