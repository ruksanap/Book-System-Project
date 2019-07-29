package com.company.bookservice.dao;

import com.company.bookservice.model.Book;

import java.util.List;

public interface BookDao {

    Book addBook(Book book);

    Book getBook(int bookId);

    List<Book> getAllBooks();

    Book updateBook(Book book);

    void deleteBook(int bookId);


}
