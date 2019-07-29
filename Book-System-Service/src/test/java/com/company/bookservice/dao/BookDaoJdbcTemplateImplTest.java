package com.company.bookservice.dao;

import com.company.bookservice.model.Book;
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
public class BookDaoJdbcTemplateImplTest {

    @Autowired
    BookDao bookDao;

    @Before
    public void setUp() throws Exception {

        List<Book> books = bookDao.getAllBooks();
        for (Book b : books) {
            bookDao.deleteBook(b.getBookId());

        }
    }

    @Test
    public void addGetDeleteBook() {
        Book b = new Book();
        b.setTitle("Book One");
        b.setAuthor("Book One Author One");

        b = bookDao.addBook(b);

        Book b1 = bookDao.getBook(b.getBookId());
        assertEquals(b1, b);

        bookDao.deleteBook(b.getBookId());
        b1 = bookDao.getBook(b.getBookId());
        assertNull(b1);

    }

    @Test
    public void getAllBooks() {
        Book b = new Book();
        b.setTitle("Book One");
        b.setAuthor("Book One Author One");

        b = bookDao.addBook(b);

        b = new Book();
        b.setTitle("Book Two");
        b.setAuthor("Book Two Author Two");

        b = bookDao.addBook(b);

        List<Book> books = bookDao.getAllBooks();
        assertEquals(2, books.size());

    }

    @Test
    public void updateBook() {
        Book b = new Book();
        b.setTitle("Book One");
        b.setAuthor("Book One Author One");

        b = bookDao.addBook(b);

        b.setAuthor("Book One Author Two");

        bookDao.updateBook(b);

        Book b1 = bookDao.getBook(b.getBookId());
        assertEquals(b1, b);
    }
}