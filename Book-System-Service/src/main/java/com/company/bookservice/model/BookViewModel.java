package com.company.bookservice.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class BookViewModel {

    private int bookId;
    @NotEmpty(message = "Please supply a title for the book.")
    private String title;
    @NotEmpty(message = "Please supply an author for the book.")
    private String author;
    private List<Note> note;

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public int getBookId(){
        return this.bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, note);
    }
}
