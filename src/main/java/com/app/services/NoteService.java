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

    public Note validateNote(Note note){
        if (note.getNoteCreator().isEmpty() || note.getNoteTitle().isEmpty() || note.getNoteBody().isEmpty()){
            return null;
        }
        noteDao.storeNote(note);
        return note;
    }
}
