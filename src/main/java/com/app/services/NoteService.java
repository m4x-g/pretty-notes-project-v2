package com.app.services;

import com.app.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    public Note validateNote(Note note){
        if (note.getNoteCreator().isEmpty() || note.getNoteTitle().isEmpty() || note.getNoteBody().isEmpty()){
            return null;
        }
        return note;
    }
}
