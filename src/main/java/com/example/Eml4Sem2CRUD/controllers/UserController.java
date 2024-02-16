package com.example.Eml4Sem2CRUD.controllers;


import com.example.Eml4Sem2CRUD.models.User;
import com.example.Eml4Sem2CRUD.sevices.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        log.info("Received request to show all users");
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        log.info("Received request to show form for adding new user");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        log.info("Received request to create new user: " + user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        log.info("Received request to delete user by id = " + id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String getUser(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        log.info("Received request to get user by id = " + id);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        log.info("Received request to update user: " + user);
        return "redirect:/users";
    }
}
