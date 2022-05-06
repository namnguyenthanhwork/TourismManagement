package com.ou.common.services.impl;


import com.ou.common.repositories.CMTourDepartureDateRepository;
import com.ou.common.services.CMTourDepartureDateService;
import com.ou.pojos.TourDepartureDateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourDepartureDateServiceImpl implements CMTourDepartureDateService {

    @Autowired
    private CMTourDepartureDateRepository cMTourDepartureDateRepository;


    @Override
    public List<TourDepartureDateEntity> getTourDepartureDateByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourDepartureDateRepository.getTourDepartureDateByTour(tourSlug.trim());
    }

    @Override
    public List<TourDepartureDateEntity> getTourDepartureDateByDepartureDate(Integer dptId) {
        if (dptId == null )
            return null;
        return cMTourDepartureDateRepository.getTourDepartureDateByDepartureDate(dptId);
    }

    @Override
    public TourDepartureDateEntity getTourDepartureDateEntity(Integer tourId, Integer dptId) {
        if (tourId == null || dptId == null)
            return null;
        return cMTourDepartureDateRepository.getTourDepartureDateEntity(tourId, dptId);
    }

    @Override
    public boolean createTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        return cMTourDepartureDateRepository.createTourDepartureDate(tourDepartureDateEntity);
    }

    @Override
    public boolean updateTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        return cMTourDepartureDateRepository.updateTourDepartureDate(tourDepartureDateEntity);
    }

    @Override
    public boolean deleteTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        return cMTourDepartureDateRepository.deleteTourDepartureDate(tourDepartureDateEntity);
    }
}
