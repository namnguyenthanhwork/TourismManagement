package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TourNote", schema = "TourismManagement")
@IdClass(TourNoteEntityPK.class)
public class TourNoteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tour_id")
    private int tourId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_id")
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
        TourNoteEntity that = (TourNoteEntity) o;
        return tourId == that.tourId && noteId == that.noteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, noteId);
    }
}
