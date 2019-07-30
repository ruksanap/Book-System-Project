package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class BookServiceTest extends AbstractTestNGSpringContextTests {

//    @Autowired
//    private MockServerManager serverManager;


    private BookDao bookDao;


    @Before
    public void setUp() throws Exception {

        setUpBookDaoMock();

    }

    @Test
    public void saveFindDeleteBook() {

        Book book = new Book();
        book.setAuthor("author");
        book.setTitle("book title");




    }

    @Test
    public void findBookById() {
    }

    @Test
    public void findAllBooks() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void deleteBook() {
    }

    public void setUpBookDaoMock(){
        bookDao = mock(BookDaoJdbcTemplateImpl.class);
//
//        Note note = new Note();
//        note.setNote("note");
//
//        Note note2 = new Note();
//        note2.setNote("note 2");
//
//        List<Note> noteList = new ArrayList<>();
//
//        noteList.add(note);
//        noteList.add(note2);


        Book book = new Book();
        book.setBookId(1);
        book.setAuthor("author");
        book.setTitle("book title");

        Book book2 = new Book();
        book2.setAuthor("author");
        book2.setTitle("book title");

        Book bookUpdate = new Book();
        bookUpdate.setBookId(1);
        bookUpdate.setAuthor("author new");
        bookUpdate.setTitle("book title");

        List<Book> bookList = new ArrayList<>();

        bookList.add(book);

        doReturn(book).when(bookDao).addBook(book);
        doReturn(book).when(bookDao).getBook(1);
        doReturn(bookList).when(bookDao).getAllBooks();
        doReturn(bookUpdate).when(bookDao).updateBook(bookUpdate);

    }


}