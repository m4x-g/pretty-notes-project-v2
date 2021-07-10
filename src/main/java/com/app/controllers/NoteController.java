package com.app.controllers;

import com.app.Note;
import com.app.PseudoDB;
import com.app.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public String getNotesPage(@RequestParam(name = "id", required = false) String userId, @RequestParam(name = "c", required = false) String category, Model model){
        if (userId == null && category == null) {
            model.addAttribute("note", noteService.getAllNotes());
        }

        if (userId != null && category == null) {
            model.addAttribute("note", noteService.getUserNotes(Long.parseLong(userId)));
        }

        if (userId == null && category != null) {
            model.addAttribute("note", noteService.getAllNotesByCategory(category));
        }

        if (userId != null && category != null) {
            model.addAttribute("note", noteService.getUserNotesByCategory(Long.parseLong(userId), category));
        }

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
        if (!noteService.validateNote(note).isValidated()){
            model.addAttribute("status", "error");
            model.addAttribute("note", note);
            return "create_note";
        }
        model.addAttribute("note", noteService.getAllNotes());
        return "redirect:/notes";
    }
}
