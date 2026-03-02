package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.User;
import com.example.potion_smith_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping(value= "/add", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }


}
