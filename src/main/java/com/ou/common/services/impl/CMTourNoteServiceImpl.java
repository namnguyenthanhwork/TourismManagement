package com.ou.common.services.impl;

import com.ou.common.repositories.CMTourNoteRepository;
import com.ou.common.services.CMTourNoteService;
import com.ou.pojos.TourNoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTourNoteServiceImpl implements CMTourNoteService {
    @Autowired
    private CMTourNoteRepository cMTourNoteRepository;

    @Override
    public List<TourNoteEntity> getTourNoteByTour(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourNoteRepository.getTourNoteByTour(tourSlug.trim());
    }

    @Override
    public List<TourNoteEntity> getTourNoteByNote(String noteSlug) {
        if (noteSlug == null || noteSlug.trim().length() == 0)
            return null;
        return cMTourNoteRepository.getTourNoteByNote(noteSlug.trim());
    }

    @Override
    public boolean createTourNote(TourNoteEntity tourNoteEntity) {
        return cMTourNoteRepository.createTourNote(tourNoteEntity);
    }

    @Override
    public boolean updateTourNote(TourNoteEntity tourNoteEntity) {
        return cMTourNoteRepository.updateTourNote(tourNoteEntity);
    }

    @Override
    public boolean deleteTourNote(TourNoteEntity tourNoteEntity) {
        return cMTourNoteRepository.deleteTourNote(tourNoteEntity);
    }
}
