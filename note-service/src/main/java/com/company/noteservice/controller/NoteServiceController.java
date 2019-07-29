package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteServiceController {

    @Autowired
    NoteDao noteDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody @Valid Note note) {
        return noteDao.addNote(note);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable("id") int noteId) {
        Note note = noteDao.getNote(noteId);
        if (note == null)
            throw new IllegalArgumentException("Note does not exist for id " + noteId);
        return note;
    }

    @GetMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNoteByBook(@PathVariable("bookId") int bookId) {
        List<Note> noteList = noteDao.getNotesByBook(bookId);
        if (noteList != null && noteList.size() == 0)
            throw new IllegalArgumentException("Note is not available for " + bookId);
        return noteList;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable("id") int noteId) {

        noteDao.deleteNote(noteId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("id") int noteId, @RequestBody @Valid Note note) {
        if (note.getNoteId() == 0)
            note.setNoteId(noteId);
        if (noteId != note.getNoteId()) {
            throw new IllegalArgumentException("Note ID provided not available");
        }
        noteDao.updateNote(note);
    }

}
