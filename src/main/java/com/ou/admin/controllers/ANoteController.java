package com.ou.admin.controllers;

import com.ou.common.services.CMNoteService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.NoteEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/ghi-chu")
public class ANoteController {
//
    @Autowired
    private CMNoteService cMNoteService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getNotesView() {
        return "a-note";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getNotesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray notes = cMNoteService.getNotes(pageIndex);
        return new ResponseEntity<>(notes, notes.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getNoteCreatedView() {
        return "a-note-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createNote(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        NoteEntity noteEntity = pojoBeanFactory.getApplicationContext().getBean(NoteEntity.class);
        noteEntity.setNoteTitle(httpServletRequest.getParameter("noteTitle"));
        noteEntity.setNoteContent(httpServletRequest.getParameter("noteContent"));
        boolean createdResult = cMNoteService.createNote(noteEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{noteSlug}")
    public String getNoteEditedView(@PathVariable String noteSlug) {
        return "a-note-updated";
    }

    @GetMapping("/{noteSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getNoteDetail(@PathVariable String noteSlug) {
        JSONObject note = cMNoteService.getNoteAsJsonObj(noteSlug);
        if (note == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @RequestMapping(value = "/{noteSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateNote(@PathVariable String noteSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        NoteEntity note = cMNoteService.getNoteAsObj(noteSlug);
        if (note == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        note.setNoteTitle(httpServletRequest.getParameter("noteTitle"));
        note.setNoteContent(httpServletRequest.getParameter("noteContent"));
        boolean updateResult = cMNoteService.updateNote(note);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{noteSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable String noteSlug) {
        NoteEntity note = cMNoteService.getNoteAsObj(noteSlug);
        if (noteSlug == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMNoteService.deleteNote(note);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
