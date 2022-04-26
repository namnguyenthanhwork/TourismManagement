package com.ou.common.services;

import com.ou.pojos.TourNoteEntity;

import java.util.List;

public interface CMTourNoteService {
    List<TourNoteEntity> getTourNoteByTour(String tourSlug);
    List<TourNoteEntity> getTourNoteByNote(String noteSlug);

    boolean createTourNote(TourNoteEntity tourNoteEntity);

    boolean updateTourNote(TourNoteEntity tourNoteEntity);

    boolean deleteTourNote(TourNoteEntity tourNoteEntity);
}
