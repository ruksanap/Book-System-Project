package com.company.bookservice.service;


import com.company.bookservice.dao.BookDao;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    private BookDao bookDao;

    @Autowired
    public BookService(BookDao newBookDao){
        this.bookDao = newBookDao;
    }

    public BookViewModel saveBook(BookViewModel book){
        return null;

    }

    public BookViewModel findBookById(Integer id){
        return null;
    }

    public List<BookViewModel> findAllBooks(){
        return null;
    }

    public BookViewModel updateBook(BookViewModel book){
        return null;
    }

    public void deleteBook(int id){

    }

    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bvm = new BookViewModel();
        return bvm;

    }

    private Book buildBook(BookViewModel bvm){
        Book book = new Book();

        return book;

    }

}
