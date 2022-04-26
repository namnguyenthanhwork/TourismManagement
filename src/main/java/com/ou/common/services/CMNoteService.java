package com.ou.common.services;

import com.ou.pojos.NoteEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMNoteService {

    JSONArray getNotes(Integer pageIndex);

    NoteEntity getNoteAsObj(String noteSlug);

    JSONObject getNoteAsJsonObj(String noteSlug);

    boolean createNote(NoteEntity noteEntity);

    boolean updateNote(NoteEntity noteEntity);

    boolean deleteNote(NoteEntity noteEntity);
}
