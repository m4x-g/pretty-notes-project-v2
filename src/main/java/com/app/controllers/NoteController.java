package com.app.controllers;

import com.app.Note;
import com.app.PseudoDB;
import com.app.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    // lol 😅
    // I will pay for this later... probably 😓
//    @Autowired
//    private NoteService noteService;

    @GetMapping("/notes")
    public String getNotesPage(Model model){
        model.addAttribute("note", PseudoDB.getNotes());
        return "notes";
    }

    @GetMapping("/notes/{userName}")
    public String getUserNotesPage(@PathVariable(value = "userName") String userName, Model model){
        model.addAttribute("note", PseudoDB.getUserNotes(userName));
        return "notes";
    }

    @GetMapping("/create_note")
    public String getCreateNotePage(Model model){
        model.addAttribute("note", new Note());
        return "create_note";
    }

    @PostMapping("/create_note")
    public String createNote(@ModelAttribute Note note, Model model){
        // logic here :)
        model.addAttribute("status", PseudoDB.validateNote(note) == null ? "error" : "success");
        if (PseudoDB.validateNote(note) == null){
            model.addAttribute("note", note);
            return "create_note";
        }
        PseudoDB.storeNote(note);
        return "note";
    }
}
