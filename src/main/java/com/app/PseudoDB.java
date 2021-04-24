package com.app;

import java.util.HashSet;

public class PseudoDB {
    public static HashSet<Note> notes = new HashSet<>();

    public static void storeNote(Note note){
        notes.add(note);
    }

    public static void printAllNotes(){
        for (Note note : notes){
            note.printNote();
        }
    }

    public static void printAllUserNotes(String user){
        for (Note note : notes){
            if (note.getNoteCreator().equals(user)){
                note.printNote();
            }
        }
    }
}
