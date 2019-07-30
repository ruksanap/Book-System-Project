package com.company.notequeue;

import com.company.notequeue.model.Note;
import com.company.notequeue.util.NoteServiceClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteListener {
    @Autowired
    private NoteServiceClient noteServiceClient;

    public NoteListener(){

    }
    public NoteListener(NoteServiceClient noteServiceClient){
        this.noteServiceClient = noteServiceClient;
    }

    @RabbitListener(queues = NoteQueueApplication.QUEUE_NAME)
    public void receiveNote(Note note){
        System.out.println(note);
        Note addedNote = null;
        if(note.getNoteId()!= 0){
            noteServiceClient.updateNote(note.getNoteId(),note);
            System.out.println("Note ID "+note.getNoteId()+"Book ID "+note.getBookId()+"Note "+note.getNote());
        }
        else {
           addedNote = noteServiceClient.createNote(note);
            System.out.println("Note ID "+addedNote.getNoteId()+"Book ID "+addedNote.getBookId()+"Note "+addedNote.getNote());
        }



    }
}
