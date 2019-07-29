package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcTemplateImplTest {

    @Autowired
    protected NoteDao noteDao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Note> mList = noteDao.getAllNotes();

        mList.stream()
                .forEach(note -> noteDao.deleteNote(note.getNoteId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteNote() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("This is a fiction");

        note = noteDao.addNote(note);
        Note note1 = noteDao.getNote(note.getNoteId());
        assertEquals(note, note1);
        noteDao.deleteNote(note.getNoteId());

        note1 = noteDao.getNote(note.getNoteId());
        assertNull(note1);
    }
    @Test
    public void addNote() {
    }

    @Test
    public void getNote() {
    }

    @Test
    public void getAllNotes() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("This is a fiction");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("This is a Science fiction");
        noteDao.addNote(note);

        List<Note> noteList = noteDao.getAllNotes();
        assertEquals(noteList.size(), 2);
    }

    @Test
    public void updateNote() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("This is a fiction");
        note = noteDao.addNote(note);

        note.setBookId(1);
        note.setNote("This is a Science fiction");
        noteDao.updateNote(note);

        Note note1 = noteDao.getNote(note.getNoteId());
        assertEquals(note1, note);
    }

    @Test
    public void deleteNote() {
    }

    @Test
    public void getNotesByBook() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("This is a fiction");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("This is a Science fiction");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(1);
        note.setNote("This is a Poetry");
        noteDao.addNote(note);

        List<Note> noteList = noteDao.getNotesByBook(1);
        assertEquals(2, noteList.size());

        noteList = noteDao.getNotesByBook(2);
        assertEquals(1, noteList.size());
    }
}