package com.app.controllers;

import com.app.User;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user, Model model){
//        model.addAttribute("status", userService.validateUser(user) == null ? "error" : "success");
        if (!userService.validateUser(user).isValidated()){
            model.addAttribute("status", "error");
            model.addAttribute("user", user);
            return "register";
        }
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/users";
    }
}
