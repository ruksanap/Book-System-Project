package com.company.bookservice.util;

import com.company.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")

public interface NoteServiceClient {

    @RequestMapping(value = "/notes/book/{bookId}", method = RequestMethod.GET)
    public List<Note> getNoteByBook(@PathVariable("bookId") int bookId);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable("id") int noteId);



}
