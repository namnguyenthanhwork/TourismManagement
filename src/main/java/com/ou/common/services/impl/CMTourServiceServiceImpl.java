package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourServiceRepository;
import com.ou.common.services.CMTourServiceService;
import com.ou.pojos.TourServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourServiceServiceImpl implements CMTourServiceService {
    @Autowired
    private CMTourServiceRepository cMTourServiceRepository;

    @Override
    public List<TourServiceEntity> getTourServiceByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourServiceRepository.getTourServiceByTour(tourSlug.trim());
    }

    @Override
    public List<TourServiceEntity> getTourServiceByService(String servSlug) {
        if (servSlug == null || servSlug.trim().length() == 0)
            return null;
        return cMTourServiceRepository.getTourServiceByService(servSlug.trim());
    }

    @Override
    public boolean createTourService(TourServiceEntity tourServiceEntity) {
        return cMTourServiceRepository.createTourService(tourServiceEntity);
    }

    @Override
    public boolean updateTourService(TourServiceEntity tourServiceEntity) {
        return cMTourServiceRepository.updateTourService(tourServiceEntity);
    }

    @Override
    public boolean deleteTourService(TourServiceEntity tourServiceEntity) {
        return cMTourServiceRepository.deleteTourService(tourServiceEntity);
    }
}
