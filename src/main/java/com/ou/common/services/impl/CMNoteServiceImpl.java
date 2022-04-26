package com.ou.common.services.impl;

import com.ou.common.repositories.CMNoteRepository;
import com.ou.common.services.CMNoteService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.NoteEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMNoteServiceImpl implements CMNoteService {

    @Autowired
    private CMNoteRepository cMNoteRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getNotes(Integer pageIndex) {
        List<Object[]> notes = cMNoteRepository.getNotes(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        notes.forEach(note -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("noteId", note[0]);
            jsonObject.put("noteTitle", note[1]);
            jsonObject.put("noteSlug", note[2]);
            jsonObject.put("noteContent", note[3]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public NoteEntity getNoteAsObj(String noteSlug) {
        if (noteSlug == null || noteSlug.trim().length() == 0)
            return null;
        return cMNoteRepository.getNote(noteSlug);
    }

    @Override
    public JSONObject getNoteAsJsonObj(String noteSlug) {
        if (noteSlug == null || noteSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        NoteEntity note = cMNoteRepository.getNote(noteSlug.trim());
        if (note == null)
            return null;
        jsonObject.put("noteId", note.getNoteId());
        jsonObject.put("noteTitle", note.getNoteTitle());
        jsonObject.put("noteSlug", note.getNoteSlug());
        jsonObject.put("noteContent", note.getNoteContent());
        return jsonObject;
    }

    @Override
    public boolean createNote(NoteEntity noteEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(noteEntity.getNoteTitle());
        noteEntity.setNoteSlug(slugUtil.getSlug());
        return cMNoteRepository.createNote(noteEntity);
    }

    @Override
    public boolean updateNote(NoteEntity noteEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(noteEntity.getNoteTitle());
        noteEntity.setNoteSlug(slugUtil.getSlug());
        return cMNoteRepository.updateSNote(noteEntity);
    }

    @Override
    public boolean deleteNote(NoteEntity noteEntity) {
        return cMNoteRepository.deleteNote(noteEntity);
    }
}
