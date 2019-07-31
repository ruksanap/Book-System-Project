package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.model.Note;
import com.company.bookservice.util.NoteServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class BookServiceTest extends AbstractTestNGSpringContextTests {

//    @Autowired
//    private MockServerManager serverManager;


    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.booksystemservice";

    private BookDao bookDao;
//
    private NoteServiceClient noteServiceClient;

    private RabbitTemplate rabbitTemplate;

    private BookService bookService;


    @Before
    public void setUp() throws Exception {

        setUpBookDaoMock();
        setUpNoteServiceMock();
        setUpRabbitTemplate();
        bookService = new BookService(bookDao, noteServiceClient, rabbitTemplate);

    }

    @Test
    public void saveFindBook() {

        BookViewModel bvm = new BookViewModel();
        bvm.setAuthor("author");
        bvm.setTitle("book title");

        bvm = bookService.saveBook(bvm);

        Book book = new Book();
        book.setBookId(bvm.getBookId());
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());

        BookViewModel bvmCheck = bookService.findBookById(book.getBookId());

        Book bookCheck = new Book();

        bookCheck.setBookId(bvmCheck.getBookId());
        bookCheck.setAuthor(bvmCheck.getAuthor());
        bookCheck.setTitle(bvmCheck.getTitle());

        assertEquals(book, bookCheck);

    }

    @Test
    public void findAllBooks() {

        BookViewModel bvm = new BookViewModel();
        bvm.setAuthor("author");
        bvm.setTitle("book title");

        bvm = bookService.saveBook(bvm);

        Book book = new Book();
        book.setBookId(bvm.getBookId());
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());

        List<BookViewModel> bvmList = bookService.findAllBooks();

        assertEquals(bvmList.size(), 1);

    }

    @Test
    public void updateBook() {

        BookViewModel bvm = new BookViewModel();
        bvm.setAuthor("author");
        bvm.setTitle("book title");

        bvm = bookService.saveBook(bvm);

        bvm.setAuthor("author new");

        BookViewModel bvmCheck = bookService.updateBook(bvm);

        assertEquals(bvm, bvmCheck);

    }


    public void setUpNoteServiceMock(){
        noteServiceClient = mock(NoteServiceClient.class);


    }


    public void setUpRabbitTemplate(){
        rabbitTemplate = mock(RabbitTemplate.class);

        Note note = new Note();
        note.setBookId(1);
        note.setNote("Author: authorTitle: book title");
        note.setNoteId(null);

    }

    public void setUpBookDaoMock(){
        bookDao = mock(BookDaoJdbcTemplateImpl.class);


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

        doReturn(book).when(bookDao).addBook(book2);
        doReturn(book).when(bookDao).getBook(1);
        doReturn(bookList).when(bookDao).getAllBooks();
        doReturn(bookUpdate).when(bookDao).updateBook(bookUpdate);

    }


}