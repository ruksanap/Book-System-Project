package com.company.bookservice.controller;

import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
@RefreshScope
public class BookController {


    @Autowired
    BookService service;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel saveBook(@RequestBody BookViewModel bookViewModel) {
        return service.saveBook(bookViewModel);
    }

//    Create Book
//===========
//    URI: /books
//    HTTP Method: POST
//    RequestBody: Book data (minus ID)
//    ResponseBody: Book data (plus autogenerated ID)
//


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private BookViewModel findBookById(@PathVariable("id") int id) {
        System.out.println("id="+id);
        BookViewModel bookViewModel = service.findBookById(id);
        if (bookViewModel == null) {
            throw new IllegalArgumentException("Book cannot be retrieved by ID provided: " + id);
        }
        return bookViewModel;
    }
//    Get Book
//========
//    URI: /books/{id}
//    HTTP Method: GET
//    RequestBody: None
//    ResponseBody: Book data
//

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> findAllBooks() {
        System.out.println(service);
        return service.findAllBooks();
    }
//    Get All Books
//=============
//    URI: /books
//    HTTP Method: GET
//    RequestBody: None
//    ResponseBody: Array of Book data
//


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public BookViewModel updateBook(@PathVariable ("id") Integer id, @RequestBody BookViewModel bookViewModel) {
        if (bookViewModel.getBookId() == 00) {
            bookViewModel.setBookId(id);
        }
        if (id != bookViewModel.getBookId()) {
            throw new IllegalArgumentException("Book ID on path must match ID of Book.");
        }

        return service.updateBook(bookViewModel);
    }
//    Update Book
//===========
//    URI: /books/{id}
//    HTTP Method: PUT
//    RequestBody: Book data
//    ResponseBody: None
//

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable ("id") int id) {

        service.deleteBook(id);
    }
//    Delete Book
//===========
//    URI: /books/{id}
//    HTTP Method: DELETE
//    RequestBody: None
//    ResponseBody: None
}
