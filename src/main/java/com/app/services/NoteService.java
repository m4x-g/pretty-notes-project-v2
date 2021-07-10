package com.app.services;

import com.app.Note;
import com.app.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    public List<Note> getAllNotes(){
       return noteDao.getAllNotes();
    }
    public List<Note> getUserNotes(long id){
       return noteDao.getUserNotes(id);
    }

    public Note validateNote(Note note){
        note.setValidated(true);

        if (note.getNoteCreator().isEmpty()) {
            note.setValidated(false);
            note.setErrors("User name is empty");
        }

        if (note.getNoteTitle().isEmpty()) {
            note.setValidated(false);
            note.setErrors("Note Tile is empty");
        }

        if (note.getNoteBody().isEmpty()) {
            note.setValidated(false);
            note.setErrors("Note text is empty");
        }

        if (note.isValidated()) {
            noteDao.storeNote(note);
        }
        return note;
    }
}
