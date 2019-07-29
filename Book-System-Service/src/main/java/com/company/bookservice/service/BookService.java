package com.company.bookservice.service;


import com.company.bookservice.dao.BookDao;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.model.Note;
import com.company.bookservice.util.NoteServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private NoteServiceClient noteServiceClient;

    public BookService() {

    }
    @Autowired
    public BookService(BookDao newBookDao, NoteServiceClient noteServiceClient){
        this.bookDao = newBookDao;
        this.noteServiceClient = noteServiceClient;
    }


    @Transactional
    public BookViewModel saveBook(BookViewModel bvm){
        Book book = buildBook(bvm);

        book = bookDao.addBook(book);

        bvm = buildBookViewModel(book);

        return bvm;

    }

    public BookViewModel findBookById(int id){

        Book book = new Book();
        book = bookDao.getBook(id);

        BookViewModel bvm = buildBookViewModel(book);

        return bvm;

    }

    public List<BookViewModel> findAllBooks(){

        List<BookViewModel> bvmList = new ArrayList<>();

        List<Book> bookList = new ArrayList<>();

        bookList = bookDao.getAllBooks();

        bookList.stream().forEach(element -> {

            BookViewModel bvm = buildBookViewModel(element);

            bvmList.add(bvm);
        });

        return bvmList;
    }

    @Transactional
    public BookViewModel updateBook(BookViewModel bvm){
        Book book = buildBook(bvm);
        book = bookDao.updateBook(book);


        return buildBookViewModel(book);

    }

    @Transactional
    public void deleteBook(int id){

        List<Note> notes = noteServiceClient.getNoteByBook(id);
        notes.forEach(note -> noteServiceClient.deleteNote(note.getNoteId()));
        bookDao.deleteBook(id);


    }

    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());

        List<Note> noteList = noteServiceClient.getNoteByBook(book.getBookId());

        bvm.setNote(noteList);

        return bvm;

    }

    private Book buildBook(BookViewModel bvm){
        Book book = new Book();
        book.setBookId(bvm.getBookId());
        book.setTitle(bvm.getTitle());
        book.setAuthor(bvm.getAuthor());

        return book;

    }

}
