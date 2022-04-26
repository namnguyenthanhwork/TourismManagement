package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourRatingRepository;
import com.ou.common.services.CMTourRatingService;
import com.ou.pojos.TourRatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourRatingServiceImpl implements CMTourRatingService {
    @Autowired
    private CMTourRatingRepository cMTourRatingRepository;

    @Override
    public List<TourRatingEntity> getTourRatingByAccount(String accUsername) {
        if (accUsername == null || accUsername.trim().length() == 0)
            return null;
        return cMTourRatingRepository.getTourRatingByAccount(accUsername.trim());
    }

    @Override
    public List<TourRatingEntity> getTourRatingByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourRatingRepository.getTourRatingByTour(tourSlug.trim());
    }

    @Override
    public boolean createTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.createTourRating(tourRatingEntity);
    }

    @Override
    public boolean updateTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.updateTourRating(tourRatingEntity);
    }

    @Override
    public boolean deleteTourRating(TourRatingEntity tourRatingEntity) {
        return cMTourRatingRepository.deleteTourRating(tourRatingEntity);
    }
}
