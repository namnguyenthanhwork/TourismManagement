package com.ou.pojos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Integer noteId;

    @Column(name = "note_title")
    private String noteTitle;

    @Column(name="note_slug")
    private String noteSlug;

    @Column(name ="note_content")
    private String noteContent;

    @Column(name = "note_is_active")
    private Boolean noteIsActive;

    @OneToMany(mappedBy = "note")
    private List<TourNote> tourNotes;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteSlug() {
        return noteSlug;
    }

    public void setNoteSlug(String noteSlug) {
        this.noteSlug = noteSlug;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Boolean getNoteIsActive() {
        return noteIsActive;
    }

    public void setNoteIsActive(Boolean noteIsActive) {
        this.noteIsActive = noteIsActive;
    }

    public List<TourNote> getTourNotes() {
        return tourNotes;
    }

    public void setTourNotes(List<TourNote> tourNotes) {
        this.tourNotes = tourNotes;
    }
}
