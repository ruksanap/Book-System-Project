package com.company.notequeue.util;

import com.company.notequeue.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note createNote(@RequestBody @Valid Note note);

    @PutMapping("notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("id") int id, @RequestBody @Valid Note note);
}
