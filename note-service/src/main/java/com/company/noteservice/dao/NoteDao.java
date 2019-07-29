package com.company.noteservice.dao;

import com.company.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

    Note addNote(Note note);

    Note getNote(int noteId);

    List<Note> getAllNotes();

    Note updateNote(Note note);

    void deleteNote(int noteId);

    List<Note> getNotesByBook(int bookId);

}
