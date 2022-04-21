package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Note", schema = "TourismManagement")
public class NoteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_id")
    private int noteId;
    @Basic
    @Column(name = "note_title")
    private String noteTitle;
    @Basic
    @Column(name = "note_slug")
    private String noteSlug;
    @Basic
    @Column(name = "note_content")
    private String noteContent;
    @Basic
    @Column(name = "note_is_active")
    private byte noteIsActive;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
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

    public byte getNoteIsActive() {
        return noteIsActive;
    }

    public void setNoteIsActive(byte noteIsActive) {
        this.noteIsActive = noteIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return noteId == that.noteId && noteIsActive == that.noteIsActive && Objects.equals(noteTitle, that.noteTitle) && Objects.equals(noteSlug, that.noteSlug) && Objects.equals(noteContent, that.noteContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, noteTitle, noteSlug, noteContent, noteIsActive);
    }
}
