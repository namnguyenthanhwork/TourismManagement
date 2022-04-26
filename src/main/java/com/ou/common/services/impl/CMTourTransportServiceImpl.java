package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourTransportRepository;
import com.ou.common.services.CMTourTransportService;
import com.ou.pojos.TourTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourTransportServiceImpl implements CMTourTransportService {
    @Autowired
    private CMTourTransportRepository cMTourTransportRepository;
    @Override
    public List<TourTransportEntity> getTourTransportByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourTransportRepository.getTourTransportByTour(tourSlug.trim());

    }

    @Override
    public List<TourTransportEntity> getTourTransportByTransport(String tranSlug) {
        if (tranSlug == null || tranSlug.trim().length() == 0)
            return null;
        return cMTourTransportRepository.getTourTransportByTransport(tranSlug.trim());
    }

    @Override
    public boolean createTourTransport(TourTransportEntity tourTransportEntity) {
        return cMTourTransportRepository.createTourTransport(tourTransportEntity);
    }

    @Override
    public boolean updateTourTransport(TourTransportEntity tourTransportEntity) {
        return cMTourTransportRepository.updateTourTransport(tourTransportEntity);
    }

    @Override
    public boolean deleteTourTransport(TourTransportEntity tourTransportEntity) {
        return cMTourTransportRepository.deleteTourTransport(tourTransportEntity);
    }
}
