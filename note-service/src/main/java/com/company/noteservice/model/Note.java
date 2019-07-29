package com.company.noteservice.model;

import java.util.Objects;

public class Note {

    private Integer noteId;
    private Integer bookId;
    private String note;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(noteId, note1.noteId) &&
                bookId.equals(note1.bookId) &&
                note.equals(note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, bookId, note);
    }
}
