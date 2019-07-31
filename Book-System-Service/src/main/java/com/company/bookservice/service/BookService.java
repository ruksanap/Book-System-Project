package com.company.bookservice.service;


import com.company.bookservice.dao.BookDao;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.model.Note;
import com.company.bookservice.util.NoteServiceClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {


    private BookDao bookDao;


    private NoteServiceClient noteServiceClient;


    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.booksystemservice";

    public BookService() {

    }
    @Autowired
    public BookService(BookDao newBookDao, NoteServiceClient noteServiceClient, RabbitTemplate rabbitTemplate){
        this.bookDao = newBookDao;
        this.noteServiceClient = noteServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Transactional
    public BookViewModel saveBook(BookViewModel bvm){
        Book book = buildBook(bvm);

        book = bookDao.addBook(book);
        System.out.println("sending Note");
        Note note = new Note();
        note.setBookId(book.getBookId());
        String noteStr = "Author: "+book.getAuthor()+"Title: "+book.getTitle();
        note.setNote(noteStr);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);
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
        // We don't know if any track have been removed so delete all associated tracks
        // and then associate the tracks in the viewModel with the album
        List<Note> noteList = noteServiceClient.getNoteByBook(book.getBookId());
        noteList.stream()
                .forEach(note -> noteServiceClient.deleteNote(note.getNoteId()));
        Note note = new Note();
        note.setBookId(book.getBookId());
        String noteStr = "Author: "+book.getAuthor()+"Title: "+book.getTitle();
        note.setNote(noteStr);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);

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
        List<Note> noteList = null;
        try {

            noteList = noteServiceClient.getNoteByBook(book.getBookId());
        }
        catch(RuntimeException re){
            re.printStackTrace();
        }

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
