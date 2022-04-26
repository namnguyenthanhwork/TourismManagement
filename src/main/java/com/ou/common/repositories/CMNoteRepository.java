package com.ou.common.repositories;


import com.ou.pojos.NoteEntity;

import java.util.List;

public interface CMNoteRepository {
    List<Object[]> getNotes(Integer pageIndex);

    NoteEntity getNote(String noteSlug);

    NoteEntity getNote(Integer noteId);

    boolean createNote(NoteEntity noteEntity);

    boolean updateSNote(NoteEntity noteEntity);

    boolean deleteNote(NoteEntity noteEntity);
}
