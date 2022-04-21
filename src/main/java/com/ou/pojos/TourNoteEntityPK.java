package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourNoteEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourId;
    @Column(name = "note_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourNoteEntityPK that = (TourNoteEntityPK) o;
        return tourId == that.tourId && noteId == that.noteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, noteId);
    }
}
