package com.app.controllers;

import com.app.PseudoDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteController {
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
}
